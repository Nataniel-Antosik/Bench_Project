package com.antosik.benchproject.app.popular.movies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antosik.benchproject.R
import com.antosik.benchproject.app.popular.movies.entity.Movie
import com.antosik.benchproject.app.popular.movies.view.PopularMoviesFragmentNavigator
import com.antosik.benchproject.domain.popular.movies.entity.toUi
import com.antosik.benchproject.domain.popular.movies.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val popularMoviesFragmentNavigator: PopularMoviesFragmentNavigator
) : ViewModel() {

    val popularMovies: LiveData<List<Movie>>
        get() = _popularMovies
    val isLoaderVisible: LiveData<Boolean>
        get() = _isLoaderVisible

    private val _popularMovies = MutableLiveData<List<Movie>>(emptyList())
    private val _isLoaderVisible = MutableLiveData<Boolean>()

    init {
        loadPopularMovie()
    }

    fun navigateToMovieDetailsFragment(movieId: Int) {
        popularMoviesFragmentNavigator.navigateToMovieDetailsFragment(movieId)
    }

    private fun loadPopularMovie() {
        viewModelScope.launch {
            _isLoaderVisible.value = false
            getPopularMoviesUseCase().fold(
                onSuccess = { moviesModel ->
                    _isLoaderVisible.value = true
                    _popularMovies.value = moviesModel.toUi()
                },
                onFailure = {
                    _isLoaderVisible.value = true
                    popularMoviesFragmentNavigator.errorSnackBar(
                        R.string.errorMessageMovies,
                        onAction = { retryLoadPopularMovie() }
                    )
                }
            )
        }
    }

    private fun retryLoadPopularMovie() {
        loadPopularMovie()
    }
}
