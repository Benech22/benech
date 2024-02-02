package fr.isen.benech.androiderestaurant

import com.google.gson.annotations.SerializedName


data class ListMenu (

  @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()

)