package com.antosik.benchproject.domain.popular.movies.usecase

import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import com.antosik.benchproject.domain.popular.movies.repo.PopularMoviesRepository
import javax.inject.Inject

class UpdatePopularMovieUseCase @Inject constructor(private val repo: PopularMoviesRepository) {

    suspend operator fun invoke(movieModel: MovieModel) {
        repo.updatePopularMovie(
            movieModel.copy(isFavourite = !movieModel.isFavourite)
        )
    }
}
