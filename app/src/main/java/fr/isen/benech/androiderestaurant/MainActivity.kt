package fr.isen.benech.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.benech.androiderestaurant.ui.theme.AndroidERestaurantTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidERestaurantTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(android.graphics.Color.parseColor("#245713"))
                ) {
                    Greeting(::onMenuClicked)
                }
            }
        }
    }

    private fun onMenuClicked(category: String) {
        Toast.makeText(this, category, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, CategoryActivity::class.java).apply {
            putExtra(CategoryActivity.CATEGORY_KEY, category)
        }
        startActivity(intent)
    }
}

@Composable
fun Greeting(onMenuClicked: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(android.graphics.Color.parseColor("#245713")))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        ) {
            // Contenu de la rangée à l'extrémité supérieure
            // Ajoutez vos éléments ici si nécessaire
        }

        Spacer(modifier = Modifier.height(16.dp)) // Ajout d'un espace vertical

        Text(
            text = "Bienvenue chez Mounier",
            color = Color.White,
            fontSize = 28.sp, // Augmenter la taille du texte
            fontWeight = FontWeight.Bold, // Mettre en gras le texte
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )


        Spacer(modifier = Modifier.height(16.dp)) // Ajout d'un espace vertical

        // Nouvelle colonne avec les boutons "Entrée", "Plat" et "Dessert"
        CategoriesButtons(onMenuClicked)
    }
}

@Composable
fun CategoriesButtons(onMenuClicked: (String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(color = Color(android.graphics.Color.parseColor("#245713")))
            .fillMaxWidth()
    ) {
        // Bouton entrée
        OutlinedButton(
            onClick = { onMenuClicked("Entrée") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Entrée", color = Color.White, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(8.dp)) // Ajout d'un espace vertical

        // Bouton Plats
        OutlinedButton(
            onClick = { onMenuClicked("Plat") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Plat", color = Color.White, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(8.dp)) // Ajout d'un espace vertical

        // Bouton Dessert
        OutlinedButton(
            onClick = { onMenuClicked("Dessert") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Dessert", color = Color.White, fontSize = 20.sp)
        }
    }
}
