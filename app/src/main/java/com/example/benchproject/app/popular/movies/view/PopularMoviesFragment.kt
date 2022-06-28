package com.example.benchproject.app.popular.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.benchproject.app.common.navigator.Navigator
import com.example.benchproject.app.popular.movies.viewModel.PopularMoviesViewModel
import com.example.benchproject.databinding.PopularMoviesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesFragment : Navigator<PopularMoviesFragmentNavigator>() {

    private val popularMoviesFragmentViewModel: PopularMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = PopularMoviesFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = popularMoviesFragmentViewModel
        binding.recyclerAdapterForPopularMovies = PopularMoviesRecyclerAdapter()
        return binding.root
    }
}
