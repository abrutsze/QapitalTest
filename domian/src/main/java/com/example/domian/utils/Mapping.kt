package com.example.domian.utils


import com.example.entity.localmodels.*
import com.example.entity.responcemodel.*

fun ActivityItemResponse.fromActivityResponseToActivityItemModel(): ActivityItem {
    return ActivityItem(
        userId = userId,
        message = message,
        amount = amount,
        timestamp = timestamp
    )
}
fun UserInfoResponse.fromUserInfoResponseToUserData(): UserData {
    return UserData(
        userId = userId,
        displayName = displayName,
        avatarUrl = avatarUrl
    )
}




