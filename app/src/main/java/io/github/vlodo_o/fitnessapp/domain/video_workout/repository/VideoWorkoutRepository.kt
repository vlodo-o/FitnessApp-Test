package io.github.vlodo_o.fitnessapp.domain.video_workout.repository

import io.github.vlodo_o.fitnessapp.domain.models.VideoWorkout

interface VideoWorkoutRepository {
    suspend fun getVideo(id: Int): VideoWorkout
}