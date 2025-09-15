package io.github.vlodo_o.fitnessapp.data.network

import io.github.vlodo_o.fitnessapp.data.network.dto.VideoWorkoutDto
import io.github.vlodo_o.fitnessapp.data.network.dto.WorkoutDto

class RetrofitNetworkClient(
    private val api: WorkoutApi
) : NetworkClient {

    override suspend fun getWorkouts(): List<WorkoutDto> {
        return api.getWorkouts()
    }

    override suspend fun getVideoWorkout(id: Int): VideoWorkoutDto {
        return api.getVideoWorkout(id)
    }
}