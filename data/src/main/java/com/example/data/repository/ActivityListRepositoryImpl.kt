package com.example.data.repository

import com.example.data.dataservice.apiservice.AllApiService
import com.example.data.dataservice.sqlservice.ActivityListDto
import com.example.data.datastore.ActivityListRepository
import com.example.entity.localmodels.ActivityItem
import com.example.entity.responcemodel.ActivityResponse
import com.example.entity.responcemodel.UserInfoResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class ActivityListRepositoryImpl(
    private val allApiService: AllApiService,
    val activityListDto: ActivityListDto
) :
    ActivityListRepository {
    override fun getUserInfo(userId: Int): Single<UserInfoResponse> =
        allApiService.getUserItem(userId)

    override fun getActivityListFromDb(): Observable<List<ActivityItem>> =
        activityListDto.getActivityList()

    override fun insetActivityListDb(data: List<ActivityItem>) =
        activityListDto.insetActivity(data)

    override fun getActivityListResponse(from: String, to: String): Observable<ActivityResponse> =
        allApiService.getActivityList(from, to)


}