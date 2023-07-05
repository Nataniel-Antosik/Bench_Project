package com.antosik.benchproject.app.popular.movies.view

import androidx.navigation.fragment.findNavController
import com.antosik.benchproject.app.common.navigator.FragmentNavigator
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
