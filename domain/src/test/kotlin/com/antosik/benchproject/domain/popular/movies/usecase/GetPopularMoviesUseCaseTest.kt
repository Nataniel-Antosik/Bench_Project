package com.antosik.benchproject.domain.popular.movies.usecase

import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import com.antosik.benchproject.domain.popular.movies.repo.PopularMoviesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class GetPopularMoviesUseCaseTest {

    val movieListModel = listOf(
        MovieModel(10001, "Test1", 5.4, "2022-06-23", "https:/something1.com", false),
        MovieModel(10002, "Test2", 6.4, "2022-06-22", "https:/something2.com", false),
        MovieModel(10003, "Test3", 3.4, "2022-06-21", "https:/something3.com", false)
    )

    val popularMoviesRepository: PopularMoviesRepository = mockk()
    val tested = GetPopularMoviesUseCase(popularMoviesRepository)

    @Test
    fun `SHOULD return MovieModel type WHEN method get data from repository`() = runTest {
        coEvery { popularMoviesRepository.getPopularMoviesList() } returns movieListModel

        tested() shouldBeEqualTo movieListModel
    }

    @Test
    fun `SHOULD return empty list WHEN method get empty list from repository`() = runTest {
        coEvery { popularMoviesRepository.getPopularMoviesList() } returns emptyList()

        tested() shouldBeEqualTo emptyList()
    }
}
