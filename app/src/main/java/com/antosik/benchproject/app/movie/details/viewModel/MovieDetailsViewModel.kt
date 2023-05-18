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
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : ViewModel() {
    val responseMovieDetails: LiveData<MovieDetails>
        get() = _responseMovieDetails
    val isLoaderVisible: LiveData<Boolean>
        get() = _isLoaderVisible
    val isScreenElementsVisible: LiveData<Boolean>
        get() = _isVisibleScreenElements
    val isPlaceholderWithButtonVisible: LiveData<Boolean>
        get() = _isPlaceholderWithButtonVisible

    private val _responseMovieDetails = MutableLiveData<MovieDetails>()
    private val _isVisibleScreenElements = MutableLiveData(false)
    private val _isLoaderVisible = MutableLiveData<Boolean>()
    private val _isPlaceholderWithButtonVisible = MutableLiveData<Boolean>()
    private val args = MovieDetailsFragmentArgs.fromSavedStateHandle(savedState)

    init {
        loadMovieDetails()
    }

    fun onRefreshClick() {
        loadMovieDetails()
    }

    private fun loadMovieDetails() {
        // TODO Probably _isPlaceholderWithButtonVisible should be moved to if?
        // TODO A lot of flags... you could think about packing it into some model to encapsulate that logic
        viewModelScope.launch {
            _isLoaderVisible.value = true
            getMovieDetailsUseCase(args.movieId).let { movieDetailsModel ->
                if (movieDetailsModel != null) {
                    _isPlaceholderWithButtonVisible.value = false
                    _responseMovieDetails.value = movieDetailsModel.toUi()
                    _isVisibleScreenElements.value = true
                } else {
                    _isPlaceholderWithButtonVisible.value = true
                    _isVisibleScreenElements.value = false
                }
            }
            _isLoaderVisible.value = false
        }
    }
}
