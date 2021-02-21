package com.example.entity.responcemodel


import com.squareup.moshi.Json

data class ActivityItemResponse(
    @Json(name = "message")
    val message: String,
    @Json(name = "amount")
    val amount: Double,
    @Json(name = "userId")
    val userId: Int,
    @Json(name = "timestamp")
    val timestamp: String
)