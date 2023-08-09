package com.antosik.benchproject.domain.favourite.usecase

import com.antosik.benchproject.domain.favourite.repo.FavouriteMoviesRepository
import javax.inject.Inject

class GetFavouriteMoviesUseCase @Inject constructor(private val repo: FavouriteMoviesRepository) {

    operator fun invoke() = repo.getFavouriteMovies()
}
