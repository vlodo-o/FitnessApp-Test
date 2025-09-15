package io.github.vlodo_o.fitnessapp.ui.workout_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.navArgs
import io.github.vlodo_o.fitnessapp.R
import io.github.vlodo_o.fitnessapp.databinding.FragmentWorkoutDetailBinding
import io.github.vlodo_o.fitnessapp.ui.converters.toDisplayName
import io.github.vlodo_o.fitnessapp.ui.converters.workoutDurationToString
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class WorkoutDetailFragment : Fragment(R.layout.fragment_workout_detail) {

    private val viewModel: WorkoutDetailViewModel by viewModel()
    private var player: ExoPlayer? = null
    private val args: WorkoutDetailFragmentArgs by navArgs()

    private lateinit var binding: FragmentWorkoutDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWorkoutDetailBinding.bind(view)

        initPlayer()
        setupWorkoutDetails()
        observeViewModel()
        viewModel.fetchVideo(args.workout.id)
    }

    private fun initPlayer() {
        player = ExoPlayer.Builder(requireContext()).build()
        binding.playerView.player = player
    }

    private fun setupWorkoutDetails() {
        val workout = args.workout
        binding.workoutTitle.text = workout.title
        binding.workoutDescription.text = workout.description
        binding.workoutType.text = workout.type.toDisplayName(requireContext())

        val durationText = workoutDurationToString(workout.duration, requireContext())
        binding.workoutDuration.text = durationText.ifEmpty { "" }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is WorkoutDetailState.Success -> handleSuccess(state)
                is WorkoutDetailState.Loading -> showLoading()
                is WorkoutDetailState.Error -> showError()
            }
        }
    }

    private fun handleSuccess(state: WorkoutDetailState.Success) {
        binding.playerView.visibility = View.VISIBLE
        binding.detailsLayout.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE

        val mediaItem = MediaItem.fromUri(state.videoWorkout.link)
        player?.setMediaItem(mediaItem)
        player?.prepare()
    }

    private fun showLoading() {
        binding.playerView.visibility = View.GONE
        binding.detailsLayout.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showError() {
        binding.playerView.visibility = View.VISIBLE
        binding.detailsLayout.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE

        Toast.makeText(requireContext(),
            getString(R.string.video_loading_error_message), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player?.release()
        player = null
    }
}