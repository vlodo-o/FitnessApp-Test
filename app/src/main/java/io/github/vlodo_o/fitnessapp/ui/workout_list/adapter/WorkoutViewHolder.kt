package io.github.vlodo_o.fitnessapp.ui.workout_list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.github.vlodo_o.fitnessapp.databinding.ItemWorkoutBinding
import io.github.vlodo_o.fitnessapp.domain.models.Workout
import io.github.vlodo_o.fitnessapp.ui.converters.toDisplayName
import io.github.vlodo_o.fitnessapp.ui.converters.workoutDurationToString

class WorkoutViewHolder(private val binding: ItemWorkoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Workout, onItemClick: (Workout) -> Unit) {
        binding.titleText.text = item.title
        binding.typeText.text = item.type.toDisplayName(binding.root.context)
        val text = workoutDurationToString(item.duration, binding.root.context)
        if (text != "") {
            binding.durationText.visibility = View.VISIBLE
            binding.divider.visibility = View.VISIBLE
            binding.durationText.text = text
        } else {
            binding.durationText.visibility = View.GONE
            binding.divider.visibility = View.GONE
        }
        binding.descriptionText.text = item.description ?: ""
        binding.root.setOnClickListener { onItemClick(item) }
    }
}