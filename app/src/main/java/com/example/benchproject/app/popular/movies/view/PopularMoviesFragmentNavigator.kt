package com.example.benchproject.app.popular.movies.view

import androidx.navigation.fragment.findNavController
import com.example.benchproject.app.common.navigator.FragmentNavigator
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class PopularMoviesFragmentNavigator @Inject constructor() : FragmentNavigator() {

    fun navigateToMovieDetailsFragment(movieId: Int) = fragment!!.findNavController()
        .navigate(
            PopularMoviesFragmentDirections.toMovieDetailsFragment(movieId)
        )
}
