package io.github.vlodo_o.fitnessapp.data.video_workout

import io.github.vlodo_o.fitnessapp.data.mappers.toDomain
import io.github.vlodo_o.fitnessapp.data.network.NetworkClient
import io.github.vlodo_o.fitnessapp.domain.models.VideoWorkout
import io.github.vlodo_o.fitnessapp.domain.video_workout.repository.VideoWorkoutRepository

class VideoWorkoutRepositoryImpl(
    private val client: NetworkClient,
    private val baseUrl: String
): VideoWorkoutRepository {
    override suspend fun getVideo(id: Int): VideoWorkout {
        return client.getVideoWorkout(id).toDomain(baseUrl)
    }
}