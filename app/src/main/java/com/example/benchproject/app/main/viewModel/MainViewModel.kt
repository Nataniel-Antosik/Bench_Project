package com.example.benchproject.app.main.viewModel

import androidx.lifecycle.ViewModel
import com.example.benchproject.app.main.entity.Movie
import com.example.benchproject.domain.main.usecase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getMovieUseCase: GetMovieUseCase) : ViewModel() {

    fun getMovieData() = getMovieUseCase().map {
        Movie(it.name, it.rating)
    }
}
