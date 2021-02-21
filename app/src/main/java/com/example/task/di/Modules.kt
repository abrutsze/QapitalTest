package com.example.task.di

import com.example.task.fragment.home.ActivitiesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ActivitiesListViewModel(get()) }
}
