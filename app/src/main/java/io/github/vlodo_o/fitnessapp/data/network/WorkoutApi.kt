package io.github.vlodo_o.fitnessapp.data.network

import io.github.vlodo_o.fitnessapp.data.network.dto.VideoWorkoutDto
import io.github.vlodo_o.fitnessapp.data.network.dto.WorkoutDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WorkoutApi {
    @GET("get_workouts")
    suspend fun getWorkouts(): List<WorkoutDto>
    @GET("get_video")
    suspend fun getVideoWorkout(
        @Query("id") id: Int
    ): VideoWorkoutDto
}