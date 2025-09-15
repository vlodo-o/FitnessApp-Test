package io.github.vlodo_o.fitnessapp.ui.converters

import android.content.Context
import io.github.vlodo_o.fitnessapp.R
import io.github.vlodo_o.fitnessapp.domain.models.WorkoutType

fun WorkoutType.toDisplayName(context: Context): String {
    val items = context.resources.getStringArray(R.array.workout_type_names)
    return items[this.ordinal + 1]
}