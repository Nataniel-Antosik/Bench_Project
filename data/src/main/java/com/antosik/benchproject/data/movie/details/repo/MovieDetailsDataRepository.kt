package com.antosik.benchproject.data.movie.details.repo

import com.antosik.benchproject.data.movie.details.entity.toEntity
import com.antosik.benchproject.data.movies.common.api.MoviesApi
import com.antosik.benchproject.data.movies.common.database.MovieDao
import com.antosik.benchproject.domain.movie.details.repo.MovieDetailsRepository

internal class MovieDetailsDataRepository(
    private val apiService: MoviesApi,
    private val dao: MovieDao
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int) = runCatching {
        apiService.getMovieDetails(movieId).let { movieDetailsRemote ->
            dao.insertMovieDetails(movieDetailsRemote.toEntity())
            dao.insertGenres(movieDetailsRemote.genres.toEntity(movieId))
        }
    }.let {
        dao.getMovieDetailsWithGenres(movieId).firstOrNull()?.toDomain()
    }
}
