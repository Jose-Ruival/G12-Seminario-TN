package com.G12SeminarioTN.API

import com.squareup.moshi.Json

data class Hit(
    @Json(name = "recipe")
    val recipe: Receta
)
