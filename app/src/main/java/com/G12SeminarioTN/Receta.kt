package com.G12SeminarioTN

import com.G12SeminarioTN.API.Ingrediente
import com.G12SeminarioTN.API.NutrienteInfo

data class Receta(
    val uri: String,                         // Identificador de ontología
    val label: String,                       // Título de la receta
    val image: String,                       // URL de la imagen
    val source: String,                      // Identificador de sitio de origen
    val url: String,                         // URL de la receta original
    val yield: Int,                          // Porciones que rinde
    val summary: String?,                    // Breve descripción
    val calories: Float?,                    // Total de energía, kcal
    val totalWeight: Float?,                 // Peso total, g
    val ingredients: List<Ingrediente>,       // Lista de ingredientes
    val totalNutrients: List<NutrienteInfo>?, // Nutrientes totales por porción
    val totalDaily: List<NutrienteInfo>?,    // % del valor diario por porción
    val dietLabels: List<String>?,           // Etiquetas de dieta
    val healthLabels: List<String>?          // Etiquetas de salud

)
