package com.G12SeminarioTN.API

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Post(
    var app_id: String,
    var app_key: String,
    var q: String,
)


