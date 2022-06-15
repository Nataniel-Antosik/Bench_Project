package com.example.benchproject.app.popular.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.benchproject.app.popular.movies.viewModel.PopularMoviesViewModel
import com.example.benchproject.databinding.PopularMoviesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {

    private val popularMoviesFragmentViewModel: PopularMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = PopularMoviesFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = popularMoviesFragmentViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}