package com.G12SeminarioTN.API

import com.G12SeminarioTN.RecetaConAtributos.Receta
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EdamamResponse(
    val q: String,
    val from: Int,
    val to: Int,
    val params: List<List<String>>,
    val count: Int,
    val more: Boolean,
    val hits: List<Receta>
)
