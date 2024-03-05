package com.repasoAndroid.androidmaster.superheroapp

import com.google.gson.annotations.SerializedName

//Una data class recibe 1 parámetro como mínimo
//(Buena práctica) @SerializedName("response") es lo primero que recibe en el JSON es response

data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superheroes: List<SuperheroItemResponse>
)

//Creamos un data class para poder acceder a cada elemento en la lista del JSON
data class SuperheroItemResponse(
    @SerializedName("id") val superheroId: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val superheroImage: SuperheroImageResponse
)

data class SuperheroImageResponse( @SerializedName("url") val url:String)

