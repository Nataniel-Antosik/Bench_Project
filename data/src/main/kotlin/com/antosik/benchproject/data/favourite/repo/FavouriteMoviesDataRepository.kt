package com.antosik.benchproject.data.favourite.repo

import com.antosik.benchproject.data.movies.common.database.dao.MovieDao
import com.antosik.benchproject.data.popular.movies.entity.database.toDomain
import com.antosik.benchproject.domain.favourite.repo.FavouriteMoviesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.map

internal class FavouriteMoviesDataRepository @Inject constructor(
    private val dao: MovieDao,
) : FavouriteMoviesRepository {

    override fun getFavouriteMovies() =
        dao.getFavouriteMovies().map {
            it.toDomain()
        }
}
