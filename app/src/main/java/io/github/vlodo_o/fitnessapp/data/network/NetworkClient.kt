package io.github.vlodo_o.fitnessapp.data.network

import io.github.vlodo_o.fitnessapp.data.network.dto.VideoWorkoutDto
import io.github.vlodo_o.fitnessapp.data.network.dto.WorkoutDto

interface NetworkClient {
    suspend fun getWorkouts(): List<WorkoutDto>
    suspend fun getVideoWorkout(id: Int): VideoWorkoutDto
}