package com.G12SeminarioTN.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostEndpoints {

    @GET("/search")
    fun searchRecipes(
        @Query("q") query: String,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
    ): Call<EdamamResponse>
}