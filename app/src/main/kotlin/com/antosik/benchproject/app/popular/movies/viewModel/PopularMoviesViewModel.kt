package com.antosik.benchproject.app.popular.movies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.antosik.benchproject.app.common.state.UIState
import com.antosik.benchproject.app.popular.movies.entity.Movie
import com.antosik.benchproject.app.popular.movies.entity.toUi
import com.antosik.benchproject.app.popular.movies.view.navigator.PopularMoviesFragmentNavigator
import com.antosik.benchproject.domain.popular.movies.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val popularMoviesFragmentNavigator: PopularMoviesFragmentNavigator,
) : ViewModel() {
    val popularMoviesViewState: LiveData<UIState>
        get() = _popularMoviesViewState

    private val _popularMoviesViewState = MutableLiveData<UIState>()
    val popularMovies = _popularMoviesViewState.map {
        it.data<List<Movie>>()
    }

    init {
        loadPopularMovies()
    }

    fun navigateToMovieDetailsFragment(movieId: Int) {
        popularMoviesFragmentNavigator.navigateToMovieDetailsFragment(movieId)
    }

    fun onRefreshPopularMovies() {
        loadPopularMovies()
    }

    private fun loadPopularMovies() {
        viewModelScope.launch {
            _popularMoviesViewState.value = UIState.Loading
            getPopularMoviesUseCase().let { movies ->
                if (movies.isEmpty()) {
                    _popularMoviesViewState.value = UIState.Empty
                } else {
                    _popularMoviesViewState.value = UIState.Success(movies.toUi())
                }
            }
        }
    }
}
