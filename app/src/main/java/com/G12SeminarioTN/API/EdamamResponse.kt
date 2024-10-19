package com.G12SeminarioTN.API

import com.G12SeminarioTN.RecetaConAtributos.Receta

data class EdamamResponse(
    val q: String?,
    val from: Int,
    val to: Int,
    val params: Array<Array<String>>,
    val count: Int,
    val more: Boolean,
    val hits: List<RecipeData>
) {
    data class RecipeData(
        val recipe: Receta
    )
}
