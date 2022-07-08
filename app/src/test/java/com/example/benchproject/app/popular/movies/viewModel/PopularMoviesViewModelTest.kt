package com.example.benchproject.app.popular.movies.viewModel

import com.example.benchproject.R
import com.example.benchproject.app.popular.movies.entity.Movie
import com.example.benchproject.app.popular.movies.view.PopularMoviesFragmentNavigator
import com.example.benchproject.domain.popular.movies.entity.MovieModel
import com.example.benchproject.domain.popular.movies.usecase.GetPopularMoviesUseCase
import com.example.benchproject.test.common.LiveDataTest
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(LiveDataTest::class)
internal class PopularMoviesViewModelTest {

    val moviesModel = listOf(
        MovieModel(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com"),
        MovieModel(10002, "Test2", 6.4, "2022-06-22", "https://image.tmdb.org/t/p/w500/something2.com"),
        MovieModel(10003, "Test3", 3.4, "2022-06-21", "https://image.tmdb.org/t/p/w500/something3.com")
    )
    val movies = listOf(
        Movie(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com"),
        Movie(10002, "Test2", 6.4, "2022-06-22", "https://image.tmdb.org/t/p/w500/something2.com"),
        Movie(10003, "Test3", 3.4, "2022-06-21", "https://image.tmdb.org/t/p/w500/something3.com")
    )
    val badResponse =
        Result.failure<List<MovieModel>>(
            Throwable("Unable to resolve host \"api.themoviedb.org\": No address associated with hostname")
        )
    val getPopularMoviesUseCase: GetPopularMoviesUseCase = mockk()
    val popularMoviesFragmentNavigator: PopularMoviesFragmentNavigator = mockk()

    @Test
    fun `when method get data from use case, it should be mapped to the movie type`() = runTest {
        coEvery { getPopularMoviesUseCase() } returns Result.success(moviesModel)
        val tested = PopularMoviesViewModel(getPopularMoviesUseCase, popularMoviesFragmentNavigator)

        tested.popularMovies.value shouldBeEqualTo movies
    }

    @Test
    fun `when method get data from use case, loader visibility should be true`() = runTest {
        coEvery { getPopularMoviesUseCase() } returns Result.success(moviesModel)
        val tested = PopularMoviesViewModel(getPopularMoviesUseCase, popularMoviesFragmentNavigator)

        tested.isLoaderVisible.value shouldBe true
    }

    @Test
    fun `when method get throwable from use case, loader visibility should be true`() = runTest {
        coEvery { getPopularMoviesUseCase() } returns badResponse
        every { popularMoviesFragmentNavigator.errorSnackBar(R.string.errorMessageMovies, onAction = any()) } just Runs
        val tested = PopularMoviesViewModel(getPopularMoviesUseCase, popularMoviesFragmentNavigator)

        tested.isLoaderVisible.value shouldBe true
    }

    @Test
    fun `when method get throwable from use case, errorSnackBar should retry request to API`() = runTest {
        val slot = slot<() -> Unit>()
        coEvery { getPopularMoviesUseCase() } returns badResponse
        every { popularMoviesFragmentNavigator.errorSnackBar(R.string.errorMessageMovies, onAction = capture(slot)) } answers { slot.captured() } andThenAnswer {}

        PopularMoviesViewModel(getPopularMoviesUseCase, popularMoviesFragmentNavigator)

        coVerify(exactly = 2) { getPopularMoviesUseCase() }
    }
}
