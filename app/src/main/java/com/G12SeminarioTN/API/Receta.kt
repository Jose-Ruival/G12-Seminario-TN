package com.G12SeminarioTN.API

import com.squareup.moshi.Json

data class Receta(
    @Json(name = "label")
    val label: String,
    @Json (name = "ingredients")
    val ingredients: List<Ingredient>,
    @Json (name = "source")
    val source: String,
    @Json (name = "url")
    val url: String,
    @Json (name = "yield")
    val yield: Float,
    @Json (name = "calories")
    val calories: Float,
    @Json (name = "cuisineType")
    val cuisineType: List<String>,
    @Json (name = "image")
    val image: String
    )



