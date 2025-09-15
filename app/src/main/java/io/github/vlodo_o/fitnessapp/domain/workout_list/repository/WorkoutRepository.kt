package io.github.vlodo_o.fitnessapp.domain.workout_list.repository

import io.github.vlodo_o.fitnessapp.domain.models.Workout

interface WorkoutRepository {
    suspend fun getWorkouts(): List<Workout>
}