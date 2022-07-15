package com.antosik.benchproject.data.popular.movies.repo

import com.antosik.benchproject.data.movies.common.api.MoviesApi
import com.antosik.benchproject.domain.popular.movies.repo.PopularMoviesRepository

internal class PopularMoviesDataRepository(
    private val apiService: MoviesApi
) : PopularMoviesRepository {

    override suspend fun getPopularMoviesList() = runCatching {
        apiService.getPopularMovies().toDomain()
    }
}
