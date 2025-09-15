package io.github.vlodo_o.fitnessapp.data.mappers

import io.github.vlodo_o.fitnessapp.data.network.dto.VideoWorkoutDto
import io.github.vlodo_o.fitnessapp.domain.models.VideoWorkout

fun VideoWorkoutDto.toDomain(baseUrl: String): VideoWorkout = VideoWorkout(
    id = id,
    duration = duration,
    link = "$baseUrl$link"
)