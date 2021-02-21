package com.example.data.dataservice.sqlservice

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.utils.Converters
import com.example.entity.localmodels.ActivityItem

@Database(
    entities = [ActivityItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun activityListDao():ActivityListDto
}