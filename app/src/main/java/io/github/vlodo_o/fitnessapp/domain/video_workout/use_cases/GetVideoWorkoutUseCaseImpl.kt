package io.github.vlodo_o.fitnessapp.domain.video_workout.use_cases

import io.github.vlodo_o.fitnessapp.domain.models.VideoWorkout
import io.github.vlodo_o.fitnessapp.domain.video_workout.repository.VideoWorkoutRepository

class GetVideoWorkoutUseCaseImpl(
    private val repository: VideoWorkoutRepository
) : GetVideoWorkoutUseCase {

    override suspend operator fun invoke(id: Int): VideoWorkout {
        return repository.getVideo(id)
    }
}