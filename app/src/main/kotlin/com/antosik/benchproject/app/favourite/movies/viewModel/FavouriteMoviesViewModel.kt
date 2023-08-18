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
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
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

    val countDownFlow = flow {
        val startingValue = 5
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    init {
        //collectFlow()
        //collectFlow2()
        //collectFlow3()
        //collectFlow3Buffer()
        //collectFlow3Conflate()
        collectFlow3CollectLatest()
    }

    private fun collectFlow3() {
        // determine what happens with the emissions so how are emissions forwarded buffer, conflate, collectLatest
        // example ordering something in the restaurant order -> appetizer, main dish, dessert
        val flow = flow {
            delay(250L) // preparing
            emit("Appetizer")
            delay(1000L) // preparing
            emit("Main dish")
            delay(100L) // preparing
            emit("Dessert")
        }
        viewModelScope.launch {
            // delay (or just suspend function) inside in collect blocking flow and when the block is
            // finished flow continue emitting
            flow.onEach {
                println("FLOW: $it is delivered")
            }.collect {
                println("FLOW: Now eating $it")
                delay(1500L) // eating
                println("FLOW: Finished eating $it")
            }
        }
    }

    private fun collectFlow3Buffer() {
        // example ordering something in the restaurant order -> appetizer, main dish, dessert
        val flow = flow {
            delay(250L) // preparing
            emit("Appetizer")
            delay(1000L) // preparing
            emit("Main dish")
            delay(100L) // preparing
            emit("Dessert")
        }
        viewModelScope.launch {
            // delay (or just suspend function) inside in collect blocking flow and when the collect statement is
            // finished flow continue emitting
            flow.onEach {
                println("FLOW: $it is delivered")
            }
                // buffer run collect on separate coroutine therefore collect operation didn't blocking flow emitting
                // and now it work faster and parallel
                .buffer()
                .collect {
                    println("FLOW: Now eating $it")
                    delay(1500L) // eating
                    println("FLOW: Finished eating $it")
                }
        }
    }

    private fun collectFlow3Conflate() {
        // example ordering something in the restaurant order -> appetizer, main dish, dessert
        val flow = flow {
            delay(250L) // preparing
            emit("Appetizer")
            delay(1000L) // preparing
            emit("Main dish")
            delay(100L) // preparing
            emit("Dessert")
        }
        viewModelScope.launch {
            // delay (or just suspend function) inside in collect blocking flow and when the collect statement is
            // finished flow continue emitting
            flow.onEach {
                println("FLOW: $it is delivered")
            }
                // conflate vs collect latest
                // we got first emit and started work on it
                // we got second emit and started work on it
                // we got third emit and started work on it
                // we finished work on third emit
                // based on it we didn't finished specific work when we got new emit `latest` emit.
                .collectLatest {
                    println("FLOW: Now eating $it")
                    delay(1500L) // eating
                    println("FLOW: Finished eating $it")
                }
        }
    }

    private fun collectFlow3CollectLatest() {
        // example ordering something in the restaurant order -> appetizer, main dish, dessert
        val flow = flow {
            delay(250L) // preparing
            emit("Appetizer")
            delay(1000L) // preparing
            emit("Main dish")
            delay(100L) // preparing
            emit("Dessert")
        }
        viewModelScope.launch {
            // delay (or just suspend function) inside in collect blocking flow and when the collect statement is
            // finished flow continue emitting
            flow.onEach {
                println("FLOW: $it is delivered")
            }
                // conflate run collect on separate coroutine
                // we got fist emit from flow we started work on it
                // we got second and third flow and we finished work on first flow
                // we don't go to work on second flow but we started work on the latest value
                // so we started work on third flow
                .conflate()
                .collect {
                    println("FLOW: Now eating $it")
                    delay(1500L) // eating
                    println("FLOW: Finished eating $it")
                }
        }
    }

    private fun collectFlow2() {
        // flattening operators
        // example:
        // normal list with lists [[1, 2], [1, 2, 3]]
        // flatten above list [ 1, 2, 1, 2, 3]
        // we can do something similar for flow
        val flow1 = flow {
            emit(1)
            delay(500L)
            emit(2)
        }
        viewModelScope.launch {
            // flatMapConcat wait for first emit and it works like emit 1 + 1 emit 2 + 1 and emit 2 + 1 and emit 2 + 2
            // flatMapMerge don't wait for first emit. Do operation at the same time
            // flatMapLatest similar like collect latest. If second value finishes before first value it will drop first value
            // and only forward with the result from second value
            // example for flatMap is we can take the whole value one time like take recipes from id 1..5 in one shot
            flow1.flatMapMerge { value ->
                flow {
                    emit(value + 1)
                    delay(500L)
                    emit(value + 2)
                }
            }.collect { value ->
                println("The value is $value")
            }
        }
    }

    private fun collectFlow() {
        // inside flow builder we can run suspend function like delay
        viewModelScope.launch {
            // simple operation map, onEach, filter
            val count = countDownFlow
                .map { time ->
                    time * time
                }
                .onEach { time ->
                    println("OPERATION The time: $time")
                }
                .filter { time ->
                    time % 2 == 0
                }
                // collect just collect value
                // collectLatest works a little different because if first flow have delay and inside
                // collect something take some time and new emit was appear collect skip operation
                // and will focus on latest value
                .count {
                    it % 2 == 0
                }
            //terminal operation reduce, fold, count. We called it terminal because we take
            // the whole result and we do something with this
            println("TERMINAL OPERATION The count for even `count` is $count")
            val reduceResult = countDownFlow
                .reduce { accumulator, value ->
                    accumulator + value
                }
            println("TERMINAL OPERATION The count for `reduce` is $reduceResult")
            val foldResult = countDownFlow
                .fold(100) { accumulator, value ->
                    accumulator + value
                }
            println("TERMINAL OPERATION The count for `fold` is $foldResult")
        }
    }
}
