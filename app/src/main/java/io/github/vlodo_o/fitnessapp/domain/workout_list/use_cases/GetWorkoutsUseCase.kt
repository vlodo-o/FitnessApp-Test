package io.github.vlodo_o.fitnessapp.domain.workout_list.use_cases

import io.github.vlodo_o.fitnessapp.domain.models.Workout

interface GetWorkoutsUseCase {
    suspend operator fun invoke(): List<Workout>
}