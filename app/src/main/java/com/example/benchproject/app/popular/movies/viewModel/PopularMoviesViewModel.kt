package com.example.benchproject.app.popular.movies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.benchproject.R
import com.example.benchproject.app.popular.movies.entity.Movie
import com.example.benchproject.app.popular.movies.view.PopularMoviesFragmentNavigator
import com.example.benchproject.domain.popular.movies.entity.toUi
import com.example.benchproject.domain.popular.movies.usecase.GetPopularMoviesUseCase
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
        getPopularMovie()
    }

    fun navigateToMovieDetailsFragment(movieId: Int) {
        popularMoviesFragmentNavigator.navigateToMovieDetailsFragment(movieId)
    }

    private fun getPopularMovie() = viewModelScope.launch {
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
                    onAction = { retryGetPopularMovie() }
                )
            }
        )
    }

    private fun retryGetPopularMovie() {
        getPopularMovie()
    }
}
