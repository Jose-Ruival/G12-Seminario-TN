package com.G12SeminarioTN.API

import com.squareup.moshi.Json

data class Receta(
    @Json(name = "name")
    val name: String,
    @Json(name = "cuisine")
    val cuisine: String,
    @Json(name = "difficulty")
    val difficulty: String
)



