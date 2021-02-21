package com.example.domian.di

import com.example.domian.interactors.ActivityListInteractor
import com.example.domian.usecase.ActivityUseCase
import org.koin.dsl.module

val interactorModule = module {
    factory <ActivityListInteractor> { ActivityUseCase(get()) }
}
