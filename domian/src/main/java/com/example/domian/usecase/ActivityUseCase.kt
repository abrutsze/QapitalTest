package com.example.domian.usecase

import android.util.Log
import com.example.data.datastore.ActivityListRepository
import com.example.domian.interactors.ActivityListInteractor
import com.example.domian.utils.*
import com.example.entity.localmodels.ActivityItem
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class ActivityUseCase(
    private val activityListRepository: ActivityListRepository
) : ActivityListInteractor {

    private val toStartDate: Date = getCurrentDateString()
    private lateinit var from: Date
    private var to: Date = toStartDate

    override fun getActivityResponse(): Observable<List<ActivityItem>> {
        from = minusDate(-14, to)
        val subscribe= activityListRepository.getActivityListResponse(
            from.dateToString(),
            to.dateToString()
        ).subscribeOn(Schedulers.io())
            .map {
                val list = it.activities.map { itemActivity ->
                    itemActivity.fromActivityResponseToActivityItemModel()
                }
                list.forEach { item ->
                    if (item.userData == null) {
                        activityListRepository.getUserInfo(item.userId)
                            .observeOn(Schedulers.io())
                            .map { userInfo ->
                                userInfo.fromUserInfoResponseToUserData()
                            }.subscribe(
                                { userInfo ->
                                    list.forEach {
                                        if (it.userId == userInfo.userId) {
                                            it.userData = userInfo
                                        }
                                    }
                                },
                                {

                                }
                            )
                    }
                }
                activityListRepository.insetActivityListDb(list)
                return@map list
            }
        to =from
        return subscribe
    }

    override fun getActivityFromDb(): Observable<List<ActivityItem>> =
        activityListRepository.getActivityListFromDb().subscribeOn(Schedulers.io())



}