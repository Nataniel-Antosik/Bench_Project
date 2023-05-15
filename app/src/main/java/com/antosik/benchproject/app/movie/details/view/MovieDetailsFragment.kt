package com.antosik.benchproject.app.movie.details.view

import android.os.Bundle
import android.view.LayoutInflater
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
    ) = MovieDetailsFragmentBinding.inflate(inflater, container, false).apply {
        lifecycleOwner = viewLifecycleOwner
        viewModel = movieDetailsViewModel
    }.root
}
