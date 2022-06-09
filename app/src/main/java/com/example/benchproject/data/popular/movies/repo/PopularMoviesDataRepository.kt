package com.example.benchproject.data.popular.movies.repo

import com.example.benchproject.data.popular.movies.api.MoviesApi
import com.example.benchproject.domain.popular.movies.entity.MovieModel
import com.example.benchproject.domain.popular.movies.repo.PopularMoviesRepository
import javax.inject.Inject

class PopularMoviesDataRepository @Inject constructor(
    private val apiService: MoviesApi
) : PopularMoviesRepository {

    override suspend fun getPopularMoviesList(): Result<List<MovieModel>> =
        runCatching {
            apiService.getPopularMovies().results.map { movieRemote ->
                MovieModel(movieRemote.name, movieRemote.rating)
            }
        }.onFailure {
            it.message
        }
}
