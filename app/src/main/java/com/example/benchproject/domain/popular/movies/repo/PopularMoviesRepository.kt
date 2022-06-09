package com.example.benchproject.domain.popular.movies.repo

import com.example.benchproject.domain.popular.movies.entity.MovieModel

interface PopularMoviesRepository {

    suspend fun getPopularMoviesList(): Result<List<MovieModel>>
}
