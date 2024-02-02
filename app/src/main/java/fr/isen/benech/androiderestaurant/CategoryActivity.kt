package fr.isen.benech.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.benech.androiderestaurant.ui.theme.AndroidERestaurantTheme
import org.json.JSONObject





sealed class Category(val name: String, val dishes: List<Plat>)

data class Plat(val name: String, val description: String)

object Entrees : Category(
    "Entrées", listOf(
        Plat("PETITE SALADE VERTE", "salade italienne & crudités de saison"),
        Plat("MINI SALUMI", "planchette de charcuterie de votre choix"),
        Plat("TARTARE DE POLIPO", "tartare de poulpe frais à la sicilienne 50g"),
        Plat("BURRATINA PESTO", "burratina 50g, Pesto Genovese, émietté de Gressins"),
    )
)

object PlatsPrincipaux : Category(
    "Plats", listOf(
        Plat("SPAGHETTI PIEMONTESE", "pâtes fraiches, pesto, jambon cuit, mozzarella"),
        Plat("TAGLIATELLE PANCETTA", "pâtes fraiches, gorgonzola, pancetta"),
        Plat("TAGLIATELLE CARBONARA", "pâtes fraiches, pecorino, œuf, pancetta"),
        Plat("LASAGNE A L’EMILIANA", "lasagne au four, sauce tomate, viande de boeuf, parmesan"),
    )
)

object Desserts : Category(
    "Desserts", listOf(
        Plat("SALADE DE FRUITS", "fruits de saison, chantilly maison"),
        Plat("Dessert 2", "Description du dessert 2"),
        // Ajoutez d'autres desserts ici
    )
)

class CategoryActivity : ComponentActivity() {



    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val category = when (intent.getStringExtra(CATEGORY_KEY)) {
            "Entrée" -> Entrees
            "Plat" -> PlatsPrincipaux
            "Dessert" -> Desserts
            else -> Entrees // Par défaut, vous pouvez choisir une catégorie par défaut
        }
        setContent {
            AndroidERestaurantTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(android.graphics.Color.parseColor("#245713"))
                ) {
                    val itemsState = remember {
                        mutableStateListOf<Items>()
                    }
                    fetchdata(itemsState)
                    Scaffold(
                        topBar = {
                            // Barre d'applications avec un bouton de retour
                            TopAppBar(
                                title = { Text(text = "Chez Mounier") },
                                navigationIcon = {
                                    IconButton(onClick = { onBackPressed() }) {
                                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                                    }
                                }
                            )
                        },
                        // Contenu principal de la page
                        content = { padding -> CategoryComponent(padding, category,itemsState, ::goToDetail) }
                    )
                }
            }
        }


    }


    companion object {
        const val CATEGORY_KEY = "category"
    }



    private fun fetchdata(itemsState: SnapshotStateList<Items>) {
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            jsonObject,
            {
                Log.d("PlatActivity", "les données en brut : $it")
                val result = Gson().fromJson(it.toString(), ListMenu::class.java)
                // Ajout des éléments à la liste itemsState
                itemsState.addAll(result.data[0].items)
            },
            {
                Log.e("PlatActivity", "error : $it")
            })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(jsonObjectRequest)
    }


    private fun goToDetail(plat: Plat) {
        val intent = Intent(this, CategoryActivity::class.java)
        startActivity(intent)

    }
}



@Composable
fun CategoryComponent(
    padding: PaddingValues,
    category: Category,
    itemsState: SnapshotStateList<Items>,
    onDishClicked: (Plat) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .background(color = Color(android.graphics.Color.parseColor("#245713")))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Aligner verticalement au centre
    ) {
        Text(
            text = category.name,
            fontSize = 24.sp,
            color = Color(android.graphics.Color.parseColor("#032757")),
            modifier = Modifier.padding(8.dp)
        )
        DishListComponent(dishes = category.dishes, onDishClicked = onDishClicked)
    }
}

@Composable
fun DishListComponent(dishes: List<Plat>, onDishClicked: (Plat) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center // Aligner verticalement au centre
    ) {
        items(dishes) { dish ->
            DishRow(dish, onDishClicked)
        }
    }
}

@Composable
fun DishRow(dish: Plat, onDishClicked: (Plat) -> Unit) {
    Card(
        modifier = Modifier
            .clickable { onDishClicked(dish) }
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally // Aligner horizontalement au centre
        ) {
            Text(text = dish.name, fontSize = 18.sp)
            Text(text = dish.description, fontSize = 14.sp)
        }
    }
}
