package com.antosik.benchproject.app.movie.details.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antosik.benchproject.app.movie.details.entity.MovieDetails
import com.antosik.benchproject.app.movie.details.entity.toUi
import com.antosik.benchproject.app.movie.details.view.MovieDetailsFragmentArgs
import com.antosik.benchproject.domain.movie.details.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    val responseMovieDetails: LiveData<MovieDetails>
        get() = _responseMovieDetails
    val isLoaderVisible: LiveData<Boolean>
        get() = _isLoaderVisible
    val isScreenElementsVisible: LiveData<Boolean>
        get() = _isVisibleScreenElements
    val isPlaceholderVisible: LiveData<Boolean>
        get() = _isPlaceholderVisible

    private val _responseMovieDetails = MutableLiveData<MovieDetails>()
    private val _isVisibleScreenElements = MutableLiveData(false)
    private val _isLoaderVisible = MutableLiveData(true)
    private val _isPlaceholderVisible = MutableLiveData(false)
    private val args = MovieDetailsFragmentArgs.fromSavedStateHandle(savedState)

    init {
        loadMovieDetails()
    }

    private fun loadMovieDetails() {
        viewModelScope.launch {
            getMovieDetailsUseCase(args.movieId).let { movieDetailsModel ->
                if (movieDetailsModel != null) {
                    _responseMovieDetails.value = movieDetailsModel.toUi()
                    _isVisibleScreenElements.value = true
                } else {
                    _isPlaceholderVisible.value = true
                    _isVisibleScreenElements.value = false
                }
            }
            _isLoaderVisible.value = false
        }
    }
}
