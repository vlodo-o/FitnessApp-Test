package io.github.vlodo_o.fitnessapp.ui.converters

import android.content.Context
import io.github.vlodo_o.fitnessapp.R

fun workoutDurationToString(duration: String, context: Context): String {
    val durationString = if (duration.matches(Regex("^\\d+$"))) duration else ""
    return if (durationString != "")
        context.resources.getQuantityString(
            R.plurals.minute_duration,
            duration.toInt(),
            duration.toInt()
        )
    else ""
}