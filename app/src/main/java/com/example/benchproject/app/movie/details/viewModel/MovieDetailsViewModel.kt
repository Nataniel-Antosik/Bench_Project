package com.example.benchproject.app.movie.details.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.benchproject.app.movie.details.entity.MovieDetails
import com.example.benchproject.domain.movie.details.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    val responseMovieDetails: LiveData<MovieDetails>
        get() = _responseMovieDetails

    private val _responseMovieDetails = MutableLiveData<MovieDetails>()

    fun getMovieDetails(movieId: Int) = viewModelScope.launch {
        getMovieDetailsUseCase(movieId).fold(
            onSuccess = { movieDetailsModel ->
                _responseMovieDetails.value = movieDetailsModel.toUi()
            },
            onFailure = {
                // TODO changed it when navigator will be implemented
                Log.d("RESPONSE", "Something went wrong")
            }
        )
    }
}
