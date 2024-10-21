package com.G12SeminarioTN.API

import com.squareup.moshi.Json

data class Receta(
    @Json(name = "name")
    val name: String,
    @Json (name = "ingredients")
    val ingredients: Array<String>,
    @Json (name = "instructions")
    val instructions: Array<String>,
    @Json (name = "prepTimeMinutes")
    val prepTimeMinutes: Int,
    @Json (name = "cookTimeMinutes")
    val cookTimeMinutes: Int,
    @Json(name = "cuisine")
    val cuisine: String,
    @Json (name = "caloriesPerServing")
    val caloriesPerServing: Int,
    @Json (name = "image")
    val image: String,
    @Json (name = "mealType")
    val mealType: String
    )



