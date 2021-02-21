package com.example.data.utils

import androidx.room.TypeConverter
import com.example.entity.localmodels.UserData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class Converters {
    @TypeConverter
    fun fromStringBlockedDetailData(json: String): UserData? {
        val jsonAdapter: JsonAdapter<UserData> =
            Moshi.Builder().build().adapter(UserData::class.java)
        return jsonAdapter.fromJson(json)
    }

    @TypeConverter
    fun fromBlockedDetailData(loginUserData: UserData?): String? {

        val jsonAdapter: JsonAdapter<UserData> =
            Moshi.Builder().build().adapter(UserData::class.java)
        return jsonAdapter.toJson(loginUserData)
    }


}