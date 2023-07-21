package com.antosik.benchproject.data.popular.movies.repo

import com.antosik.benchproject.data.BuildConfig
import com.antosik.benchproject.data.movies.common.api.MoviesApi
import com.antosik.benchproject.data.movies.common.database.dao.MovieDao
import com.antosik.benchproject.data.popular.movies.entity.database.MovieEntity
import com.antosik.benchproject.data.popular.movies.entity.database.toDomain
import com.antosik.benchproject.data.popular.movies.entity.database.toEntity
import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import com.antosik.benchproject.domain.popular.movies.repo.PopularMoviesRepository

internal class PopularMoviesDataRepository(
    private val apiService: MoviesApi,
    private val dao: MovieDao,
) : PopularMoviesRepository {

    override suspend fun getPopularMoviesList(): List<MovieModel> {
        runCatching {
            dao.insertMovies(fetchAndMergeMoviesWithLocalData())
        }
        return dao.getMovies().toDomain()
    }

    override suspend fun updatePopularMovie(movieModel: MovieModel) {
        dao.updateMovie(movieModel.toEntity())
    }

    private suspend fun fetchAndMergeMoviesWithLocalData(): List<MovieEntity> {
        val entityMovies = dao.getMovies()
        val remoteMovies = apiService.getPopularMovies(BuildConfig.API_KEY)
        return remoteMovies.results.map { remoteMovie ->
            val entityMovie = entityMovies.find { it.movieId == remoteMovie.id }
            remoteMovie.toEntity(entityMovie?.isFavorite)
        }
    }
}
