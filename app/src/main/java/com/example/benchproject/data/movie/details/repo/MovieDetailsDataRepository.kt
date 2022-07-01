package com.example.benchproject.data.movie.details.repo

import com.example.benchproject.data.movies.common.api.MoviesApi
import com.example.benchproject.domain.movie.details.repo.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsDataRepository @Inject constructor(
    private val apiService: MoviesApi
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int) = runCatching {
        apiService.getMovieDetails(movieId).toDomain()
    }
}
