package com.G12SeminarioTN.API

import com.squareup.moshi.Json

data class Recetas(
    @Json(name = "recipes")
    val recipes: Array<Receta>
)
