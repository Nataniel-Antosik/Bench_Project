package com.example.benchproject.app.movie.details.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.benchproject.R
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
    val isLoaderVisible: LiveData<Boolean>
        get() = _isLoaderVisible
    val isScreenElementsVisible: LiveData<Boolean>
        get() = _isVisibleScreenElements

    private val _responseMovieDetails = MutableLiveData<MovieDetails>()
    private val _isVisibleScreenElements = MutableLiveData(true)
    private val _isLoaderVisible = MutableLiveData(false)
    private val args = MovieDetailsFragmentArgs.fromSavedStateHandle(savedState)

    init {
        getMovieDetails()
    }

    private fun getMovieDetails() = viewModelScope.launch {
        getMovieDetailsUseCase(args.movieId).fold(
            onSuccess = { movieDetailsModel ->
                _isVisibleScreenElements.value = false
                _isLoaderVisible.value = true
                _responseMovieDetails.value = movieDetailsModel.toUi()
            },
            onFailure = {
                _isVisibleScreenElements.value = true
                _isLoaderVisible.value = true
                movieDetailsFragmentNavigator.errorSnackBar(R.string.errorMessageMovies)
            }
        )
    }
}
