package com.example.benchproject.domain.movie.details.repo

import com.example.benchproject.domain.movie.details.entity.MovieDetailsModel

interface MovieDetailsRepository {

    suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsModel>
}
