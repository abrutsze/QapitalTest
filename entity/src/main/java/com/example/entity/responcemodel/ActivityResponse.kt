package com.example.entity.responcemodel


import com.squareup.moshi.Json

data class ActivityResponse(
    @Json(name = "oldest")
    val oldest: String,
    @Json(name = "activities")
    val activities: List<ActivityItemResponse>
)