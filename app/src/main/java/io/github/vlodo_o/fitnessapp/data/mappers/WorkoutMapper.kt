package io.github.vlodo_o.fitnessapp.data.mappers

import io.github.vlodo_o.fitnessapp.data.network.dto.WorkoutDto
import io.github.vlodo_o.fitnessapp.domain.models.Workout
import io.github.vlodo_o.fitnessapp.domain.models.WorkoutType

fun WorkoutDto.toDomain(): Workout = Workout(
    id = id,
    title = title,
    description = description,
    type = WorkoutType.fromInt(type),
    duration = duration
)
