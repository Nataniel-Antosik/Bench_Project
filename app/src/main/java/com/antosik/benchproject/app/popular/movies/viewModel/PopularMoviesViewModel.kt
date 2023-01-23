package com.antosik.benchproject.app.popular.movies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antosik.benchproject.app.popular.movies.entity.Movie
import com.antosik.benchproject.app.popular.movies.entity.toUi
import com.antosik.benchproject.app.popular.movies.view.PopularMoviesFragmentNavigator
import com.antosik.benchproject.domain.popular.movies.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val popularMoviesFragmentNavigator: PopularMoviesFragmentNavigator
) : ViewModel() {

    val popularMovies: LiveData<List<Movie>>
        get() = _popularMovies
    val isLoaderVisible: LiveData<Boolean>
        get() = _isLoaderVisible
    val isEmptyDatabase: LiveData<Boolean>
        get() = _isEmptyDatabase

    private val _popularMovies = MutableLiveData<List<Movie>>(emptyList())
    private val _isLoaderVisible = MutableLiveData<Boolean>()
    private val _isEmptyDatabase = MutableLiveData(false)

    init {
        loadPopularMovie()
    }

    fun navigateToMovieDetailsFragment(movieId: Int) {
        popularMoviesFragmentNavigator.navigateToMovieDetailsFragment(movieId)
    }

    private fun loadPopularMovie() {
        viewModelScope.launch {
            _isLoaderVisible.value = true
            val movies = getPopularMoviesUseCase()
            if (movies.isEmpty()) {
                _isEmptyDatabase.value = true
            } else {
                _popularMovies.value = movies.toUi()
            }
            _isLoaderVisible.value = false
        }
    }
}
