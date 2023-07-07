package com.antosik.benchproject.app.popular.movies.view.navigator

import androidx.navigation.fragment.findNavController
import com.antosik.benchproject.app.common.navigator.FragmentNavigator
import com.antosik.benchproject.app.popular.movies.view.PopularMoviesFragmentDirections
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class PopularMoviesFragmentNavigator @Inject constructor() : FragmentNavigator() {

    fun navigateToMovieDetailsFragment(movieId: Int) {
        requireFragment().findNavController().navigate(
            PopularMoviesFragmentDirections.toMovieDetailsFragment(movieId)
        )
    }
}
