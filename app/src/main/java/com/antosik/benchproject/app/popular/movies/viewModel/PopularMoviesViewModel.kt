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
    val isPlaceholderVisible: LiveData<Boolean>
        get() = _isPlaceholderVisible
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    private val _popularMovies = MutableLiveData<List<Movie>>(emptyList())
    private val _isLoaderVisible = MutableLiveData<Boolean>()
    private val _isPlaceholderVisible = MutableLiveData<Boolean>()
    private val _isRefreshing = MutableLiveData<Boolean>()

    init {
        loadPopularMovie()
    }

    fun navigateToMovieDetailsFragment(movieId: Int) {
        popularMoviesFragmentNavigator.navigateToMovieDetailsFragment(movieId)
    }

    fun onRefreshPopularMovies() {
        loadPopularMovie()
    }

    private fun loadPopularMovie() {
        viewModelScope.launch {
            _isPlaceholderVisible.value = false
            _isLoaderVisible.value = true
            val movies = getPopularMoviesUseCase()
            if (movies.isEmpty()) {
                _isPlaceholderVisible.value = true
            } else {
                _popularMovies.value = movies.toUi()
            }
            _isRefreshing.value = false
            _isLoaderVisible.value = false
        }
    }
}
