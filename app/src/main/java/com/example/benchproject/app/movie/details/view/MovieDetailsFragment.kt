package com.example.benchproject.app.movie.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.benchproject.app.common.navigator.Navigator
import com.example.benchproject.app.movie.details.viewModel.MovieDetailsViewModel
import com.example.benchproject.databinding.MovieDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Navigator<MovieDetailsFragmentNavigator>() {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = movieDetailsViewModel
        return binding.root
    }
}
