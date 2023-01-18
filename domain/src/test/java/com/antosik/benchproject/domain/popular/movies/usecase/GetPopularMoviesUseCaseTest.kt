package com.antosik.benchproject.domain.popular.movies.usecase

import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import com.antosik.benchproject.domain.popular.movies.repo.PopularMoviesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetPopularMoviesUseCaseTest {

    val movieListModel = listOf(
        MovieModel(10001, "Test1", 5.4, "2022-06-23", "https:/something1.com"),
        MovieModel(10002, "Test2", 6.4, "2022-06-22", "https:/something2.com"),
        MovieModel(10003, "Test3", 3.4, "2022-06-21", "https:/something3.com")
    )

    val popularMoviesRepository: PopularMoviesRepository = mockk()
    val tested = GetPopularMoviesUseCase(popularMoviesRepository)

    @Test
    fun `When method get data from repository, it should return movie model type`() = runTest {
        coEvery { popularMoviesRepository.getPopularMoviesList() } returns movieListModel

        tested() shouldBeEqualTo movieListModel
    }

    @Test
    fun `When method get empty list from repository, it should return empty list`() = runTest {
        coEvery { popularMoviesRepository.getPopularMoviesList() } returns emptyList()

        tested() shouldBeEqualTo emptyList()
    }
}
