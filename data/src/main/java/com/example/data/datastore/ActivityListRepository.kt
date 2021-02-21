package com.example.data.datastore

import com.example.entity.localmodels.ActivityItem
import com.example.entity.responcemodel.ActivityResponse
import com.example.entity.responcemodel.UserInfoResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ActivityListRepository {
     fun getActivityListResponse(from:String, to:String): Observable<ActivityResponse>
     fun getActivityListFromDb(): Observable<List<ActivityItem>>
     fun insetActivityListDb(data:List<ActivityItem>)
     fun getUserInfo(userId:Int): Single<UserInfoResponse>
}