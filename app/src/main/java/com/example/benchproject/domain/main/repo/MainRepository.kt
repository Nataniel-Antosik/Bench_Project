package com.example.benchproject.domain.main.repo

import com.example.benchproject.domain.main.entity.MovieModel

interface MainRepository {

    fun getMovieList(): List<MovieModel>
}
