package com.example.benchproject.data.popular.movies.repo

import com.example.benchproject.data.movies.common.MoviesApi
import com.example.benchproject.domain.popular.movies.entity.MovieModel
import com.example.benchproject.domain.popular.movies.repo.PopularMoviesRepository
import javax.inject.Inject

class PopularMoviesDataRepository @Inject constructor(
    private val apiService: MoviesApi
) : PopularMoviesRepository {

    override suspend fun getPopularMoviesList(): Result<List<MovieModel>> =
        runCatching {
            apiService.getPopularMovies().results.map { movieRemote ->
                MovieModel(
                    movieRemote.id,
                    movieRemote.name,
                    movieRemote.rating,
                    movieRemote.releaseDate,
                    BASE_URL_FOR_IMAGE + movieRemote.imagePath
                )
            }
        }.onFailure {
            it.message
        }

    companion object {
        const val BASE_URL_FOR_IMAGE = "https://image.tmdb.org/t/p/w500"
    }
}
