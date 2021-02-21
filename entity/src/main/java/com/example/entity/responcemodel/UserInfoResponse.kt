package com.example.entity.responcemodel


import com.squareup.moshi.Json

data class UserInfoResponse(
    @Json(name = "userId")
    val userId: Int,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "avatarUrl")
    val avatarUrl: String
)