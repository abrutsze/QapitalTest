package com.example.data.dataservice.sqlservice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.entity.localmodels.ActivityItem
import io.reactivex.rxjava3.core.Observable


@Dao
interface ActivityListDto {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetActivity(data:List<ActivityItem>)

    @Query("SELECT * FROM Activity")
    fun getActivityList(): Observable<List<ActivityItem>>

}