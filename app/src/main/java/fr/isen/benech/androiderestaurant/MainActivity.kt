package fr.isen.benech.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import fr.isen.benech.androiderestaurant.ui.theme.AndroidERestaurantTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidERestaurantTheme {
                val Context = LocalContext.current
                // Use Box to set the background image for the entire app
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {



                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Image of a pizza with clip and content scale

                        Text(
                            text = "Bienvenue chez MOUNIER",
                            style = MaterialTheme.typography.displayMedium,
                            modifier = Modifier
                                .padding(8.dp)
                                .padding(8.dp)

                        )




                        // Bouttons pour entré, plats, desserts
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {

                            Button(
                                onClick = {

                                    showToast("Entrées clicked")

                                    val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
                                    intent.putExtra("menuType", "Entrées")
                                    intent.putExtra("title", "Entrées")
                                    Context.startActivity(intent)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Blue
                                ),
                                modifier = Modifier
                                    .size(110.dp)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text("Entrées")
                            }
                            // I want to add spaces between the buttons
                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = {
                                    // Handle button click for PLAT
                                    showToast("Plats clicked")

                                    val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
                                    intent.putExtra("menuType", "Plats")
                                    intent.putExtra("title", "Plats") // or "Plats" or "Dessert"
                                    Context.startActivity(intent)

                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Blue
                                ),
                                modifier = Modifier
                                    .size(110.dp)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text("Plats")
                            }

                            Button(
                                onClick = {
                                    // Gérer le clic sur le bouton DESSERT
                                    showToast("Desserts cliqués")

                                    val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
                                    intent.putExtra("menuType", "Desserts")
                                    intent.putExtra("title", "Desserts") // ou "Plats" ou "Dessert"

                                    Context.startActivity(intent)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Blue
                                ),
                                modifier = Modifier
                                    .size(110.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                            {
                                Text("Desserts")
                            }



                        }
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}