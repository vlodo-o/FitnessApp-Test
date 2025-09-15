package io.github.vlodo_o.fitnessapp.ui.workout_detail

import io.github.vlodo_o.fitnessapp.domain.models.VideoWorkout

sealed class WorkoutDetailState {
    object Loading : WorkoutDetailState()
    data class Success(val videoWorkout: VideoWorkout) : WorkoutDetailState()
    object Error : WorkoutDetailState()
}