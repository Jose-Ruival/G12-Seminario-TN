package com.G12SeminarioTN.API

import com.squareup.moshi.Json

data class Medida(
    @Json(name = "label")
    val label : String
)
