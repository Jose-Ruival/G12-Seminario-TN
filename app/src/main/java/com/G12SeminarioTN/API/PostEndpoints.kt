package com.G12SeminarioTN.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostEndpoints {



    @GET("/search")
    fun searchRecipes(
        @Query ("app_id") app_id:String,
        @Query ("app_key") app_key:String,
        @Query ("q") q:String,
        @Query ("from") from:Int,
        @Query ("to") to:Int,
        @Query ("mealType") mealType: String? = "",
        @Query ("cuisineType") cuisineType: String? = ""
    ): Call<Recetas>
}