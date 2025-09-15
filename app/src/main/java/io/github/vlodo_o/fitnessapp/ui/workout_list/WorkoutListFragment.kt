package io.github.vlodo_o.fitnessapp.ui.workout_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import io.github.vlodo_o.fitnessapp.R
import io.github.vlodo_o.fitnessapp.databinding.FragmentWorkoutListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import io.github.vlodo_o.fitnessapp.domain.models.Workout
import io.github.vlodo_o.fitnessapp.domain.models.WorkoutType
import io.github.vlodo_o.fitnessapp.ui.workout_list.adapter.WorkoutListAdapter

class WorkoutListFragment : Fragment(R.layout.fragment_workout_list) {

    private val viewModel: WorkoutListViewModel by viewModel()
    private lateinit var binding: FragmentWorkoutListBinding
    private lateinit var adapter: WorkoutListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWorkoutListBinding.bind(view)

        initRecyclerView()
        initSearchView()
        initTypeFilterSpinner()
        initRefreshButton()
        observeViewModel()

        viewModel.fetchWorkouts()
    }

    private fun initRecyclerView() {
        adapter = WorkoutListAdapter { selectedWorkout ->
            val action = WorkoutListFragmentDirections
                .actionWorkoutListFragmentToWorkoutDetailFragment(selectedWorkout)
            findNavController().navigate(action)
        }
        binding.workoutRecyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is WorkoutListState.Loading -> showLoading()
                is WorkoutListState.Success -> showWorkouts(state.workouts)
                is WorkoutListState.Error -> showErrorMessage()
                WorkoutListState.Empty -> showEmptyMessage()
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.workoutRecyclerView.visibility = View.GONE
        binding.errorLayout.visibility = View.GONE
    }

    private fun showWorkouts(workouts: List<Workout>) {
        adapter.setWorkoutList(workouts)
        binding.progressBar.visibility = View.GONE
        binding.workoutRecyclerView.visibility = View.VISIBLE
        binding.errorLayout.visibility = View.GONE
    }

    private fun showErrorMessage() {
        binding.progressBar.visibility = View.GONE
        binding.workoutRecyclerView.visibility = View.GONE
        binding.errorLayout.visibility = View.VISIBLE
        Toast.makeText(requireContext(), getString(R.string.workouts_loading_error_message), Toast.LENGTH_SHORT).show()
        binding.errorText.text = getString(R.string.workouts_loading_error_message)
    }

    private fun showEmptyMessage() {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(requireContext(),
            getString(R.string.empty_result_message), Toast.LENGTH_SHORT).show()
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchByTitle(query.orEmpty())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchByTitle(newText.orEmpty())
                return true
            }
        })
    }

    private fun initRefreshButton() {
        binding.refreshButton.setOnClickListener { viewModel.fetchWorkouts() }
    }

    private fun initTypeFilterSpinner() {
        val typeNames = resources.getStringArray(R.array.workout_type_names)

        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            typeNames
        ).apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        binding.typeFilterSpinner.adapter = spinnerAdapter

        binding.typeFilterSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val type = if (position == 0) null
                    else WorkoutType.entries[position - 1].value
                    viewModel.filterByType(type)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }
}