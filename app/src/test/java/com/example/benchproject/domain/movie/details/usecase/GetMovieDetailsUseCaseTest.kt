package com.example.benchproject.domain.movie.details.usecase

import com.example.benchproject.domain.movie.details.entity.GenresModel
import com.example.benchproject.domain.movie.details.entity.MovieDetailsModel
import com.example.benchproject.domain.movie.details.repo.MovieDetailsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetMovieDetailsUseCaseTest {
    private val movieDetailsModel = Result.success(
        MovieDetailsModel(
            54431,
            "https://image.tmdb.org/t/p/w500/something1Background.com",
            "Test1",
            listOf(
                GenresModel(1, "name1"),
                GenresModel(2, "name2"),
                GenresModel(3, "name3")
            ),
            "Example description",
            2000000,
            5.4,
            "2022-02-10",
            "https://image.tmdb.org/t/p/w500/something1.com"
        )
    )
    private val badResponseFromRepository =
        Result.failure<MovieDetailsModel>(
            Throwable("Unable to resolve host \"api.themoviedb.org\": No address associated with hostname")
        )
    private val moviesDetailsRepository: MovieDetailsRepository = mockk()
    private val movieId = 54431
    private val tested = GetMovieDetailsUseCase(moviesDetailsRepository)

    @Test
    fun `when method get data from repository, it should return movie details model`() = runTest {
        coEvery { moviesDetailsRepository.getMovieDetails(movieId) } returns movieDetailsModel

        tested(movieId) shouldBeEqualTo movieDetailsModel
    }

    @Test
    fun `when method get throwable from repository, 'is failure' data status should be true`() = runTest {
        coEvery { moviesDetailsRepository.getMovieDetails(movieId) } returns badResponseFromRepository

        tested(movieId).isFailure shouldBe true
    }

    @Test
    fun `when method get data from repository, 'is failure' data status shouldn't be true`() = runTest {
        coEvery { moviesDetailsRepository.getMovieDetails(movieId) } returns movieDetailsModel

        tested(movieId).isFailure shouldBe false
    }

    @Test
    fun `when method get throwable from repository, object should hold exception`() = runTest {
        coEvery { moviesDetailsRepository.getMovieDetails(movieId) } returns badResponseFromRepository

        tested(movieId) shouldBeEqualTo badResponseFromRepository
    }
}
