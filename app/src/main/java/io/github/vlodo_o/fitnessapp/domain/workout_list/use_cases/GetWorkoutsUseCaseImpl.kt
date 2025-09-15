package io.github.vlodo_o.fitnessapp.domain.workout_list.use_cases

import io.github.vlodo_o.fitnessapp.domain.workout_list.repository.WorkoutRepository
import io.github.vlodo_o.fitnessapp.domain.models.Workout


class GetWorkoutsUseCaseImpl(
    private val repository: WorkoutRepository
) : GetWorkoutsUseCase {
    override suspend operator fun invoke(): List<Workout> = repository.getWorkouts()
}