package com.antosik.benchproject.domain.popular.movies.usecase

import com.antosik.benchproject.domain.popular.movies.repo.PopularMoviesRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

internal class UpdatePopularMovieUseCaseTest {

    val popularMovieRepository = mockk<PopularMoviesRepository>()
    val tested = UpdatePopularMovieUseCase(popularMovieRepository)

    @Test
    fun `SHOULD called updatePopularMovie WHEN updatePopularMovieUseCase was invoked`() = runTest {
        coEvery { popularMovieRepository.updatePopularMovie(any()) } just Runs

        tested(mockk(relaxed = true))

        coVerify { popularMovieRepository.updatePopularMovie(any()) }
    }
}
