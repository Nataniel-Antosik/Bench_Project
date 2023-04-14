package com.antosik.benchproject.data.popular.movies.repo

import com.antosik.benchproject.data.movies.common.api.MoviesApi
import com.antosik.benchproject.data.movies.common.database.MovieDao
import com.antosik.benchproject.data.popular.movies.entity.toDomain
import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import com.antosik.benchproject.domain.popular.movies.repo.PopularMoviesRepository

internal class PopularMoviesDataRepository(
    private val apiService: MoviesApi,
    private val dao: MovieDao,
) : PopularMoviesRepository {

    override suspend fun getPopularMoviesList(): List<MovieModel> {
        runCatching {
            val remoteMovies = apiService.getPopularMovies()
            dao.insertMovies(remoteMovies.toEntity())
        }
        return dao.getMovies().toDomain()
    }
}
