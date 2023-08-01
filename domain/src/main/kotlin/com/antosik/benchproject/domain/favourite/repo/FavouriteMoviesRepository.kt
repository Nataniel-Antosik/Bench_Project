package com.antosik.benchproject.domain.favourite.repo

import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import kotlinx.coroutines.flow.Flow

interface FavouriteMoviesRepository {

    fun getFavouriteMovies(): Flow<List<MovieModel>>
}
