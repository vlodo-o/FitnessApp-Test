package io.github.vlodo_o.fitnessapp.ui.workout_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.vlodo_o.fitnessapp.domain.models.Workout
import io.github.vlodo_o.fitnessapp.domain.workout_list.use_cases.GetWorkoutsUseCase
import kotlinx.coroutines.launch

class WorkoutListViewModel(
    private val getWorkoutsUseCase: GetWorkoutsUseCase
) : ViewModel() {

    private val _state = MutableLiveData<WorkoutListState>()
    val state: LiveData<WorkoutListState> = _state

    private var fullWorkoutList: List<Workout> = emptyList()
    private var currentQuery: String = ""
    private var currentType: Int? = null


    fun fetchWorkouts() {
        _state.value = WorkoutListState.Loading
        viewModelScope.launch {
            try {
                fullWorkoutList = getWorkoutsUseCase()
                applyFilters()
            } catch (e: Exception) {
                _state.value = WorkoutListState.Error
            }
        }
    }

    fun searchByTitle(query: String) {
        currentQuery = query
        applyFilters()
    }

    fun filterByType(type: Int?) {
        currentType = type
        applyFilters()
    }

    private fun applyFilters() {
        if (fullWorkoutList.isEmpty()) return
        var filtered = fullWorkoutList

        currentType?.let { type ->
            filtered = filtered.filter { it.type.value == type }
        }

        if (currentQuery.isNotBlank()) {
            filtered = filtered.filter { it.title.contains(currentQuery, ignoreCase = true) }
        }

        if (filtered.isEmpty()) {
            _state.value = WorkoutListState.Empty
        }
        else {
            _state.value = WorkoutListState.Success(filtered)
        }

    }
}