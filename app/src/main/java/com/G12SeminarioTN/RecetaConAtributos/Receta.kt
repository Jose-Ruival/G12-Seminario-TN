package com.G12SeminarioTN.RecetaConAtributos

data class Receta(
    val uri: String,
    val label: String,
    val image: String,
    val source: String,
    val url: String,
    val yield: Int,
    val summary: String?,
    val calories: Float?,
    val totalWeight: Float?,
    val ingredients: List<Ingrediente>,
    val totalNutrients: List<NutrienteInfo>?,
    val totalDaily: List<NutrienteInfo>?,
    val dietLabels: List<String>?,
    val healthLabels: List<String>?
)
