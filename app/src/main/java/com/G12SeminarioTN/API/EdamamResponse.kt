package com.G12SeminarioTN.API

import com.G12SeminarioTN.Receta

data class EdamamResponse(
    val q: String?,
    val from: Int,
    val to: Int,
    val params: Array<Array<String>>,
    val count: Int,
    val more: Boolean,
    val hits: List<Receta>
)
