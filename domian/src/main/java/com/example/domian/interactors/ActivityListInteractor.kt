package com.example.domian.interactors

import com.example.entity.localmodels.ActivityItem
import io.reactivex.rxjava3.core.Observable

interface ActivityListInteractor {
    fun getActivityResponse(): Observable<List<ActivityItem>>
    fun getActivityFromDb(): Observable<List<ActivityItem>>
}