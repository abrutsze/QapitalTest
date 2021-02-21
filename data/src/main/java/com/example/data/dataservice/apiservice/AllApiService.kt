package com.example.data.dataservice.apiservice

import com.example.entity.responcemodel.ActivityResponse
import com.example.entity.responcemodel.UserInfoResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


import retrofit2.http.*

interface AllApiService {

    @GET("/users/{user_id}")
    fun getUserItem(
        @Path("user_id", encoded = true) userId: Int
    ): Single<UserInfoResponse>

    @GET("activities")
    fun getActivityList(@Query("from") from: String, @Query("to")to: String): Observable<ActivityResponse>

}
