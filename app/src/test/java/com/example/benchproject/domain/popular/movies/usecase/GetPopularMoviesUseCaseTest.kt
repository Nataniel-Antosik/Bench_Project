package com.example.benchproject.domain.popular.movies.usecase

import com.example.benchproject.domain.popular.movies.entity.MovieModel
import com.example.benchproject.domain.popular.movies.repo.PopularMoviesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetPopularMoviesUseCaseTest {

    val movieListModel = Result.success(
        listOf(
            MovieModel(10001, "Test1", 5.4, "2022-06-23", "https:/something1.com"),
            MovieModel(10002, "Test2", 6.4, "2022-06-22", "https:/something2.com"),
            MovieModel(10003, "Test3", 3.4, "2022-06-21", "https:/something3.com")
        )
    )
    val badResponseFromRepository =
        Result.failure<List<MovieModel>>(
            Throwable("Unable to resolve host \"api.themoviedb.org\": No address associated with hostname")
        )
    val popularMoviesRepository: PopularMoviesRepository = mockk()
    val tested = GetPopularMoviesUseCase(popularMoviesRepository)

    @Test
    fun `When method get data from repository, it should return movie model type`() = runTest {
        coEvery { popularMoviesRepository.getPopularMoviesList() } returns movieListModel

        tested() shouldBeEqualTo movieListModel
    }

    @Test
    fun `When method get throwable from repository, 'is failure' data status should be true`() = runTest {
        coEvery { popularMoviesRepository.getPopularMoviesList() } returns badResponseFromRepository

        tested().isFailure shouldBe true
    }

    @Test
    fun `When method get data from repository, 'is failure' data status shouldn't be true`() = runTest {
        coEvery { popularMoviesRepository.getPopularMoviesList() } returns movieListModel

        tested().isFailure shouldBe false
    }

    @Test
    fun `When method get throwable from repository, object should hold exception`() = runTest {
        coEvery { popularMoviesRepository.getPopularMoviesList() } returns badResponseFromRepository

        tested() shouldBeEqualTo badResponseFromRepository
    }
}
