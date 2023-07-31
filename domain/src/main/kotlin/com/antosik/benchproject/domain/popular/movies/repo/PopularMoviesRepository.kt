package com.antosik.benchproject.domain.popular.movies.repo

import com.antosik.benchproject.domain.popular.movies.entity.MovieModel

interface PopularMoviesRepository {

    suspend fun getPopularMoviesList(): List<MovieModel>

    suspend fun updatePopularMovie(movieModel: MovieModel)
}
