package com.example.benchproject.app.movie.details.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.benchproject.app.movie.details.entity.MovieDetails
import com.example.benchproject.app.movie.details.view.MovieDetailsFragmentArgs
import com.example.benchproject.app.movie.details.view.MovieDetailsFragmentNavigator
import com.example.benchproject.domain.movie.details.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val movieDetailsFragmentNavigator: MovieDetailsFragmentNavigator
) : ViewModel() {

    val responseMovieDetails: LiveData<MovieDetails>
        get() = _responseMovieDetails

    private val _responseMovieDetails = MutableLiveData<MovieDetails>()
    private val args = MovieDetailsFragmentArgs.fromSavedStateHandle(savedState)

    fun getMovieDetails() = viewModelScope.launch {
        getMovieDetailsUseCase(args.movieId).fold(
            onSuccess = { movieDetailsModel ->
                _responseMovieDetails.value = movieDetailsModel.toUi()
            },
            onFailure = {
                movieDetailsFragmentNavigator.errorSnackBar("Something went wrong")
            }
        )
    }
}
