package com.G12SeminarioTN.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostEndpoints {

    @GET("/posts")
    fun getPosts() : Call<List<Post>>

    @GET("/posts/{app_id}")
    fun getPost(@Path("app_id") app_id: String) : Call<Post>

    @GET("search")
    fun searchRecipes(
        @Query("q") query: String?,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Query("from") from: Int? = 0,
        @Query("to") to: Int? = 10,
        @Query("diet") diet: String? = null,
        @Query("health") health: String? = null,
        @Query("calories") calories: String? = null
    ): Call<EdamamResponse>
}