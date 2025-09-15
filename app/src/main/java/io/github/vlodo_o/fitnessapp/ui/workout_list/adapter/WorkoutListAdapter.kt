package io.github.vlodo_o.fitnessapp.ui.workout_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.vlodo_o.fitnessapp.databinding.ItemWorkoutBinding
import io.github.vlodo_o.fitnessapp.domain.models.Workout

class WorkoutListAdapter(
    private val onItemClick: (Workout) -> Unit
) : RecyclerView.Adapter<WorkoutViewHolder>() {
    private var items: List<Workout> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWorkoutBinding.inflate(inflater, parent, false)
        return WorkoutViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) =
        holder.bind(items[position], onItemClick)

    override fun getItemCount() = items.size

    fun setWorkoutList(newItems: List<Workout>) {
        items = newItems
        notifyDataSetChanged()
    }
}