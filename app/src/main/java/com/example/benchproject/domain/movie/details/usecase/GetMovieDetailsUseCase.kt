package com.example.benchproject.domain.movie.details.usecase

import com.example.benchproject.domain.movie.details.repo.MovieDetailsRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repo: MovieDetailsRepository) {

    suspend operator fun invoke(movieId: Int) = repo.getMovieDetails(movieId)
}
