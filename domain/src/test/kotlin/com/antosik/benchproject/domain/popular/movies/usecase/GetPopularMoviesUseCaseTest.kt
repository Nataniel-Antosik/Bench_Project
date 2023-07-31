package com.antosik.benchproject.domain.popular.movies.usecase

import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import com.antosik.benchproject.domain.popular.movies.repo.PopularMoviesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class GetPopularMoviesUseCaseTest {

    val movieListModel: List<MovieModel> = mockk()
    val popularMoviesRepository: PopularMoviesRepository = mockk()
    val tested = GetPopularMoviesUseCase(popularMoviesRepository)

    @Test
    fun `SHOULD return MovieModel WHEN method get data from repository`() = runTest {
        coEvery { popularMoviesRepository.getPopularMoviesList() } returns movieListModel

        tested() shouldBeEqualTo movieListModel
    }

    @Test
    fun `SHOULD return empty list WHEN method get empty list from repository`() = runTest {
        coEvery { popularMoviesRepository.getPopularMoviesList() } returns emptyList()

        tested() shouldBeEqualTo emptyList()
    }
}
