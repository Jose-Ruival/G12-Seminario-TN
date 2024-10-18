package com.G12SeminarioTN.API

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetroFitClient {

    private val BASE_URL = "https://api.edamam.com"

    // Crear la instancia de Moshi
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()) // Agregar soporte para Kotlin
        .build()

    // Crear la instancia de Retrofit
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi)) // Usar Moshi como convertidor
        .build()

    // Crear el servicio API
    val apiService: PostEndpoints = retrofit.create(PostEndpoints::class.java)
}