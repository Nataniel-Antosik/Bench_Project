package com.antosik.benchproject.app.favourite.movies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antosik.benchproject.app.common.state.UIState.Empty
import com.antosik.benchproject.app.common.state.UIState.Success
import com.antosik.benchproject.app.popular.movies.entity.toUi
import com.antosik.benchproject.domain.favourite.usecase.GetFavouriteMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

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

    // StateFlow based on name it is used to keep state
    // It is just like LiveData without life cycle awareness
    // therefore it don't now when activity go to the background
    // it keep exactly one single value. This behaviour makes it
    // hot flow. To remaining if flow is clod and any collector
    // don't collect value flow won't do anything
    // hot flow (StateFlow) it will do something if there are no collectors
    // if we assign new value to state flow then we change that value
    // even if there are no collectors

    // similar like live data we create one private mutable variable which only
    // ViewModel can change and we create one public variable to expose it
    // beyond ViewModel therefore our UI can subscribe this variable and
    // can collect value

    // if I would like use state flow with xml I should use lifecycle scope
    // with repeat on lifecycle to make lifecycle aware my stateflow and
    // make it safer. Thanks to that my UI component will be updating
    // In compare with livedata state flow can be better to use
    // because we can still use method from flow map flatmap etc.

    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    fun incrementCounter() {
        _stateFlow.value += 1
    }

    // SharedFlow is used to send one-time events, for example when
    // we need to show the same data even if device was rotated we
    // should use something to repeat it like StateFlow. But if we
    // navigate to some view we should do this only one times,
    // therefore SharedFlow will help us to reach it
    // SharedFlow also like StateFlow is hot flow it works even
    // if do not have any collectors. If SharedFlow works and don't have
    // any collectors the event will be LOST.

    // in sharedFlow we don't have builder like in flow to show how
    // we emit some value. Now we can use sharedFlow in any place in VM
    // to send an event into this (sharedFlow) at any time
    private val _sharedFlow = MutableSharedFlow<Int>() // we don't have to adding initial value because we just send events into that
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        // so emit function now suspends the square function
        // as long as all of the collectors of that sharedFlow
        // need to process it.
        // So first collect needs two second to process it
        // and second collect needs three second to process it

        // squareNumber(3) if we put function here nothing happened
        // because sharedFlow start work and don't have any collector
        // therefore we lost event
        viewModelScope.launch {
            sharedFlow.collect {
                delay(2000L)
                println("FIRST FLOW: The received number is $it")
            }
        }
        viewModelScope.launch {
            sharedFlow.collect {
                delay(3000L)
                println("SECOND FLOW: The received number is $it")
            }
        }
        // now if we put here function we can got events because we have
        // declare collector on the SharedFlow
        squareNumber(3)

        // we can use function before collector but we should add parameter to
        // sharedFlow replay - the number of values replayed to new subscribers
        // thanks to that we can cachet at max some value eg 5
        // quick recap:
        // StateFlow is used to keep state to keep values to re-emit on screen rotations
        // SharedFlow is used for one-time events. For example when we would like show
        // error snack bar one time and don't repeat it after screen rotation
    }

    fun squareNumber(number: Int) {
        // suspend as long as all of the shared flows collectors need
        viewModelScope.launch {
            _sharedFlow.emit(number * number)
        }
    }
}
