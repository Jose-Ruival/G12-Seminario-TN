package com.G12SeminarioTN.API

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostEndpoints {

    @GET("/recipes")
    fun searchRecipes(): Call<Recetas>
}