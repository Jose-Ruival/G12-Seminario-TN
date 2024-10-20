package com.G12SeminarioTN.API

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface PostEndpoints {

    @GET("/recipes")
    fun searchRecipes(): Call<Recetas>
}