package com.example.entity.localmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Activity")
data class ActivityItem(
    @ColumnInfo(name = "userId")
    val userId: Int,
    @ColumnInfo(name = "message")
    val message: String,
    @ColumnInfo(name = "amount")
    val amount: Double,
    @PrimaryKey
    @ColumnInfo(name = "timestamp")
    val timestamp: String,
    @ColumnInfo(name = "userData")
    var userData: UserData?=null
)
