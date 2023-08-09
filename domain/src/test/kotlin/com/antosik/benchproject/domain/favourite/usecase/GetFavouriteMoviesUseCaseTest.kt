package com.antosik.benchproject.domain.favourite.usecase

import com.antosik.benchproject.domain.favourite.repo.FavouriteMoviesRepository
import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class GetFavouriteMoviesUseCaseTest {

    val favouriteMoviesRepository: FavouriteMoviesRepository = mockk()
    val tested = GetFavouriteMoviesUseCase(favouriteMoviesRepository)

    @Test
    fun `SHOULD return favourite movies WHEN data incoming from repository`() = runTest {
        val movies = listOf(mockk<MovieModel>())
        every { favouriteMoviesRepository.getFavouriteMovies() } returns flowOf(movies)

        tested().first() shouldBeEqualTo movies
    }
}
