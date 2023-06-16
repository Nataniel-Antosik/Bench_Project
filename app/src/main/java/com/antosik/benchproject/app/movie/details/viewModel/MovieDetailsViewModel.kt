package com.antosik.benchproject.app.movie.details.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.antosik.benchproject.app.common.state.UIState
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
    val movieDetailsViewState: LiveData<UIState>
        get() = _movieDetailsViewState
    private val _movieDetailsViewState = MutableLiveData<UIState>()
    val responseMovieDetails = _movieDetailsViewState.map {
        it.data<MovieDetails>()
    }
    private val args = MovieDetailsFragmentArgs.fromSavedStateHandle(savedState)

    init {
        loadMovieDetails()
    }

    fun onRefreshClick() {
        loadMovieDetails()
    }

    private fun loadMovieDetails() {
        viewModelScope.launch {
            _movieDetailsViewState.value = UIState.Loading
            getMovieDetailsUseCase(args.movieId).let { movieDetailsModel ->
                if (movieDetailsModel != null) {
                    _movieDetailsViewState.value = UIState.Success(movieDetailsModel.toUi())
                } else {
                    _movieDetailsViewState.value = UIState.Empty
                }
            }
        }
    }
}
