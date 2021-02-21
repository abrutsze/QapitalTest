package com.example.entity.localmodels

import androidx.room.ColumnInfo

data class UserData(
    @ColumnInfo(name = "userId")
    val userId: Int,
    @ColumnInfo(name = "displayName")
    val displayName: String,
    @ColumnInfo(name = "avatarUrl")
    val avatarUrl: String)