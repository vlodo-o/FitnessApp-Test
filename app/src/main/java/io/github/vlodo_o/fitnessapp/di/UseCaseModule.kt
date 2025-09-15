package io.github.vlodo_o.fitnessapp.di

import io.github.vlodo_o.fitnessapp.domain.video_workout.use_cases.GetVideoWorkoutUseCase
import io.github.vlodo_o.fitnessapp.domain.video_workout.use_cases.GetVideoWorkoutUseCaseImpl
import io.github.vlodo_o.fitnessapp.domain.workout_list.use_cases.GetWorkoutsUseCase
import io.github.vlodo_o.fitnessapp.domain.workout_list.use_cases.GetWorkoutsUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetWorkoutsUseCase> { GetWorkoutsUseCaseImpl(get()) }
    factory<GetVideoWorkoutUseCase> { GetVideoWorkoutUseCaseImpl(get()) }
}