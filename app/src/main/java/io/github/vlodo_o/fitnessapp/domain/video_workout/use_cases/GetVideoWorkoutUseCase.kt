package io.github.vlodo_o.fitnessapp.domain.video_workout.use_cases

import io.github.vlodo_o.fitnessapp.domain.models.VideoWorkout

interface GetVideoWorkoutUseCase {
    suspend operator fun invoke(id: Int): VideoWorkout
}