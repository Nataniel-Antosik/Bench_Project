package com.example.benchproject.app.popular.movies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.benchproject.app.popular.movies.entity.Movie
import com.example.benchproject.app.popular.movies.view.PopularMoviesFragmentNavigator
import com.example.benchproject.domain.popular.movies.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val popularMoviesFragmentNavigator: PopularMoviesFragmentNavigator
) : ViewModel() {

    val responsePopularMovie: LiveData<List<Movie>>
        get() = _responsePopularMovie

    private val _responsePopularMovie = MutableLiveData<List<Movie>>(emptyList())

    init {
        getPopularMovie()
    }

    private fun getPopularMovie() = viewModelScope.launch {
        getPopularMoviesUseCase().fold(
            onSuccess = {
                _responsePopularMovie.value = it.map { movieModel ->
                    Movie(
                        movieModel.id,
                        movieModel.name,
                        movieModel.rating,
                        movieModel.releaseDate,
                        movieModel.imageUrl
                    )
                }
            },
            onFailure = {
                popularMoviesFragmentNavigator.errorSnackBar("Something went wrong")
            }
        )
    }
}
