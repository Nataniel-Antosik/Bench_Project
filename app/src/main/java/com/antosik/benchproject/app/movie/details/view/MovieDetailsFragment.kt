package com.antosik.benchproject.app.movie.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.antosik.benchproject.app.common.navigator.NavigableFragment
import com.antosik.benchproject.app.movie.details.viewModel.MovieDetailsViewModel
import com.antosik.benchproject.databinding.MovieDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : NavigableFragment<MovieDetailsFragmentNavigator>() {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = movieDetailsViewModel
        return binding.root
    }
}
