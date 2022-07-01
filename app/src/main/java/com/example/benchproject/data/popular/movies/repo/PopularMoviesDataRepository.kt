package com.example.benchproject.data.popular.movies.repo

import com.example.benchproject.data.movies.common.api.MoviesApi
import com.example.benchproject.data.movies.common.constants.MoviesConstants.BASE_URL_FOR_IMAGE
import com.example.benchproject.domain.popular.movies.entity.MovieModel
import com.example.benchproject.domain.popular.movies.repo.PopularMoviesRepository
import javax.inject.Inject

class PopularMoviesDataRepository @Inject constructor(
    private val apiService: MoviesApi
) : PopularMoviesRepository {

    override suspend fun getPopularMoviesList() = runCatching {
        apiService.getPopularMovies().results
            .map { movieRemote ->
                MovieModel(
                    movieRemote.id,
                    movieRemote.name,
                    movieRemote.rating,
                    movieRemote.releaseDate,
                    BASE_URL_FOR_IMAGE + movieRemote.imagePath
                )
            }
    }
}
