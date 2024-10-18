package com.G12SeminarioTN.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostEndpoints {

    @GET("/posts")
    fun getPosts() : Call<List<Post>>

    @GET("/posts/{app_id}")
    fun getPost(@Path("app_id") app_id: String) : Call<Post>

}