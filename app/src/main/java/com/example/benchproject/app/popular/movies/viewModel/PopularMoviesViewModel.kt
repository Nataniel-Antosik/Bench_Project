package com.example.benchproject.app.popular.movies.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.benchproject.app.popular.movies.entity.Movie
import com.example.benchproject.domain.popular.movies.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    // TODO On this moment is String type to show data when I will create recyclerView I change type this livedata
    val responsePopularMovie: LiveData<String>
        get() = _responsePopularMovie

    private val _responsePopularMovie = MutableLiveData<String>()

    init {
        getPopularMovie()
    }

    private fun getPopularMovie() = viewModelScope.launch {
        getPopularMoviesUseCase().fold(
            onSuccess = {
                _responsePopularMovie.value = it.map { movieModel ->
                    Movie(movieModel.name, movieModel.rating)
                }.toString()
            },
            onFailure = {
                // TODO Implement navigator to this fragment and use Snackbar by navigator
                Log.d("RESPONSE", "Something went wrong")
            }
        )
    }
}
