package com.antosik.benchproject.data.movie.details.repo

import com.antosik.benchproject.data.BuildConfig
import com.antosik.benchproject.data.movie.details.entity.database.relations.MovieDetailsGenresCrossRef
import com.antosik.benchproject.data.movie.details.entity.remote.toEntity
import com.antosik.benchproject.data.movies.common.api.MoviesApi
import com.antosik.benchproject.data.movies.common.database.dao.MovieDao
import com.antosik.benchproject.domain.movie.details.entity.MovieDetailsModel
import com.antosik.benchproject.domain.movie.details.repo.MovieDetailsRepository
import javax.inject.Inject

internal class MovieDetailsDataRepository @Inject constructor(
    private val apiService: MoviesApi,
    private val dao: MovieDao,
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int): MovieDetailsModel? {
        runCatching {
            apiService.getMovieDetails(movieId, BuildConfig.API_KEY).let { movieDetailsRemote ->
                dao.insertMovieDetails(movieDetailsRemote.toEntity())
                dao.insertGenres(movieDetailsRemote.genres.toEntity())
                movieDetailsRemote.genres.forEach { genresRemote ->
                    dao.insertMovieDetailsGenresCrossRef(
                        MovieDetailsGenresCrossRef(movieId, genresRemote.id)
                    )
                }
            }
        }
        return dao.getMovieDetailsWithGenres(movieId).firstOrNull()?.toDomain()
    }
}
