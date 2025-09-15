package io.github.vlodo_o.fitnessapp.ui.workout_list

import io.github.vlodo_o.fitnessapp.domain.models.Workout

sealed class WorkoutListState {
    object Loading : WorkoutListState()
    data class Success(val workouts: List<Workout>) : WorkoutListState()
    object Empty : WorkoutListState()
    object Error : WorkoutListState()
}