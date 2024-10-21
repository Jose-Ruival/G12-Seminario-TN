package com.G12SeminarioTN.API

import com.squareup.moshi.Json

data class Ingredient(
    @Json(name = "quantity")
    val quantity: Float,
    @Json(name = "measure")
    val measure: String?,
    @Json(name = "weight")
    val weight : Float,
    @Json(name = "food")
    val food : String
)
