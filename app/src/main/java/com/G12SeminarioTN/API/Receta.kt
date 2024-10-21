package com.G12SeminarioTN.API

import com.squareup.moshi.Json

data class Receta(
    @Json(name = "name")
    val name: String,
    @Json (name = "ingredients")
    val ingredients: List<String>,
    @Json (name = "instructions")
    val instructions: List<String>,
    @Json (name = "prepTimeMinutes")
    val prepTimeMinutes: Int,
    @Json (name = "cookTimeMinutes")
    val cookTimeMinutes: Int,
    @Json(name = "cuisine")
    val cuisine: String,
    @Json (name = "caloriesPerServing")
    val caloriesPerServing: Int,
    @Json (name = "image")
    val image: String
    )



