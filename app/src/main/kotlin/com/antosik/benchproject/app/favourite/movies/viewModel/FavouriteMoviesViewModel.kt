package com.antosik.benchproject.app.favourite.movies.viewModel

import androidx.lifecycle.ViewModel
import com.antosik.benchproject.app.common.state.UIState.Empty
import com.antosik.benchproject.app.common.state.UIState.Success
import com.antosik.benchproject.app.popular.movies.entity.toUi
import com.antosik.benchproject.domain.favourite.usecase.GetFavouriteMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

@HiltViewModel
class FavouriteMoviesViewModel @Inject constructor(
    private val getFavouriteMoviesUseCase: GetFavouriteMoviesUseCase,
) : ViewModel() {

    val favouriteMoviesViewState = flow {
        getFavouriteMoviesUseCase().collect { moviesModel ->
            if (moviesModel.isNotEmpty()) {
                emit(Success(moviesModel.toUi()))
            } else {
                emit(Empty)
            }
        }
    }
}
