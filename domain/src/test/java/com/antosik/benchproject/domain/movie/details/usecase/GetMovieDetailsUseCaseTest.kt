package com.antosik.benchproject.domain.movie.details.usecase

import com.antosik.benchproject.domain.movie.details.entity.GenresModel
import com.antosik.benchproject.domain.movie.details.entity.MovieDetailsModel
import com.antosik.benchproject.domain.movie.details.repo.MovieDetailsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetMovieDetailsUseCaseTest {
    val movieDetailsModel = Result.success(
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
    val badResponseFromRepository =
        Result.failure<MovieDetailsModel>(
            Throwable("Unable to resolve host \"api.themoviedb.org\": No address associated with hostname")
        )
    val moviesDetailsRepository: MovieDetailsRepository = mockk()
    val movieId = 54431
    val tested = GetMovieDetailsUseCase(moviesDetailsRepository)

    @Test
    fun `when method get data from repository, it should return movie details model`() = runTest {
        coEvery { moviesDetailsRepository.getMovieDetails(movieId) } returns movieDetailsModel

        tested(movieId) shouldBeEqualTo movieDetailsModel
    }

    @Test
    fun `when getMovieDetails called it takes movieId should use proper movieId`() = runTest {
        coEvery { moviesDetailsRepository.getMovieDetails(any()) } returns movieDetailsModel

        tested(movieId)

        coVerify { moviesDetailsRepository.getMovieDetails(movieId) }
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
