package com.antosik.benchproject.app.popular.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.antosik.benchproject.app.common.navigator.NavigableFragment
import com.antosik.benchproject.app.popular.movies.viewModel.PopularMoviesViewModel
import com.antosik.benchproject.databinding.PopularMoviesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesFragment : NavigableFragment<PopularMoviesFragmentNavigator>() {

    private val popularMoviesFragmentViewModel: PopularMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = PopularMoviesFragmentBinding.inflate(inflater, container, false).apply {
        lifecycleOwner = viewLifecycleOwner
        viewModel = popularMoviesFragmentViewModel
        popularMoviesRecyclerView.adapter = PopularMoviesRecyclerAdapter(popularMoviesFragmentViewModel)
    }.root
}
