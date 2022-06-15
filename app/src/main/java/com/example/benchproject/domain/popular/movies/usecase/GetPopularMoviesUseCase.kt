package com.example.benchproject.domain.popular.movies.usecase

import com.example.benchproject.domain.popular.movies.repo.PopularMoviesRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val repo: PopularMoviesRepository) {

    suspend operator fun invoke() = repo.getPopularMoviesList()
}
