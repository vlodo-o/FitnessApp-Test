package io.github.vlodo_o.fitnessapp.di

import io.github.vlodo_o.fitnessapp.ui.workout_detail.WorkoutDetailViewModel
import io.github.vlodo_o.fitnessapp.ui.workout_list.WorkoutListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { WorkoutListViewModel(get()) }
    viewModel { WorkoutDetailViewModel(get()) }
}