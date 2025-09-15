package io.github.vlodo_o.fitnessapp.data.workout_list

import io.github.vlodo_o.fitnessapp.data.mappers.toDomain
import io.github.vlodo_o.fitnessapp.data.network.NetworkClient
import io.github.vlodo_o.fitnessapp.domain.models.Workout
import io.github.vlodo_o.fitnessapp.domain.workout_list.repository.WorkoutRepository

class WorkoutRepositoryImpl (
    private val client: NetworkClient
): WorkoutRepository {
    override suspend fun getWorkouts(): List<Workout> {
        return client.getWorkouts().map { it.toDomain() }
    }
}