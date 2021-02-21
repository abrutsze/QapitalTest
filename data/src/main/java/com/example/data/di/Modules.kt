package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.dataservice.apiservice.AllApiService
import com.example.data.dataservice.sqlservice.AppDatabase
import com.example.data.datastore.ActivityListRepository
import com.example.data.repository.ActivityListRepositoryImpl
import com.example.data.utils.HeaderInterceptor
import com.example.entity.Constants.Companion.BASE_URL
import com.squareup.moshi.Moshi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory


import retrofit2.converter.moshi.MoshiConverterFactory

val apiModule = module {

    single { Moshi.Builder().build() }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .apply {
                client(
                    OkHttpClient.Builder()
                        .addInterceptor(HeaderInterceptor())
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .build()
                )
            }
            .build()
    }
    single<AllApiService> { get<Retrofit>().create(AllApiService::class.java) }

}
val databaseModule = module {
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "ActivityDB")
            .allowMainThreadQueries()
            .build()
    }
    single { provideDatabase(androidApplication()).activityListDao() }

}
val repositoryModule = module {
    single<ActivityListRepository> { ActivityListRepositoryImpl(get(),get()) }
}