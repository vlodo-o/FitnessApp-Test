package io.github.vlodo_o.fitnessapp.di

import io.github.vlodo_o.fitnessapp.data.video_workout.VideoWorkoutRepositoryImpl
import io.github.vlodo_o.fitnessapp.data.workout_list.WorkoutRepositoryImpl
import io.github.vlodo_o.fitnessapp.domain.video_workout.repository.VideoWorkoutRepository
import io.github.vlodo_o.fitnessapp.domain.workout_list.repository.WorkoutRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<WorkoutRepository> { WorkoutRepositoryImpl(get()) }
    single<VideoWorkoutRepository> { VideoWorkoutRepositoryImpl(get(), get(named("videoBaseUrl"))) }
}