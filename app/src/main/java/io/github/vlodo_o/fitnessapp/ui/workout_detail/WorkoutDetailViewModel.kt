package io.github.vlodo_o.fitnessapp.ui.workout_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.vlodo_o.fitnessapp.domain.video_workout.use_cases.GetVideoWorkoutUseCase
import kotlinx.coroutines.launch

class WorkoutDetailViewModel(
    private val getVideoWorkoutUseCase: GetVideoWorkoutUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<WorkoutDetailState>()
    val state: LiveData<WorkoutDetailState> = _state

    fun fetchVideo(id: Int) {
        _state.value = WorkoutDetailState.Loading
        viewModelScope.launch {
            try {
                val video = getVideoWorkoutUseCase(id)
                _state.value = WorkoutDetailState.Success(video)
            } catch (e: Exception) {
                _state.value = WorkoutDetailState.Error
            }
        }
    }
}