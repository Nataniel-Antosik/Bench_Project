package com.example.benchproject.app.movie.details.viewModel

import com.example.benchproject.R
import com.example.benchproject.app.movie.details.entity.Genres
import com.example.benchproject.app.movie.details.entity.MovieDetails
import com.example.benchproject.app.movie.details.view.MovieDetailsFragmentArgs
import com.example.benchproject.app.movie.details.view.MovieDetailsFragmentNavigator
import com.example.benchproject.domain.movie.details.entity.GenresModel
import com.example.benchproject.domain.movie.details.entity.MovieDetailsModel
import com.example.benchproject.domain.movie.details.usecase.GetMovieDetailsUseCase
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
internal class MovieDetailsViewModelTest {

    val movieDetailsModel = MovieDetailsModel(
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
    val movieDetails = MovieDetails(
        54431,
        "https://image.tmdb.org/t/p/w500/something1Background.com",
        "Test1",
        listOf(
            Genres(1, "name1"),
            Genres(2, "name2"),
            Genres(3, "name3")
        ),
        "Example description",
        2000000,
        5.4,
        "2022-02-10",
        "https://image.tmdb.org/t/p/w500/something1.com"
    )
    val badResponse =
        Result.failure<MovieDetailsModel>(
            Throwable("Unable to resolve host \"api.themoviedb.org\": No address associated with hostname")
        )
    val movieId = 54431
    val getMovieDetailsUseCase: GetMovieDetailsUseCase = mockk()
    val movieDetailsFragmentNavigator: MovieDetailsFragmentNavigator = mockk()
    val fragmentArgs = MovieDetailsFragmentArgs(movieId)

    @Test
    fun `when getMovieDetailsUseCase called and load data, it should be movie details model type`() = runTest {
        coEvery { getMovieDetailsUseCase(any()) } returns Result.success(movieDetailsModel)
        val tested = MovieDetailsViewModel(fragmentArgs.toSavedStateHandle(), getMovieDetailsUseCase, movieDetailsFragmentNavigator)

        tested.responseMovieDetails.value shouldBeEqualTo movieDetails
    }

    @Test
    fun `when getMovieDetailsUseCase called and load throwable, errorSnackBar should retry request to API`() = runTest {
        val slot = slot<() -> Unit>()
        coEvery { getMovieDetailsUseCase(movieId) } returns badResponse andThen Result.success(movieDetailsModel)
        every { movieDetailsFragmentNavigator.errorSnackBar(R.string.errorMessageMovies, onAction = capture(slot)) } answers { slot.captured.invoke() }

        MovieDetailsViewModel(fragmentArgs.toSavedStateHandle(), getMovieDetailsUseCase, movieDetailsFragmentNavigator)

        coVerify { getMovieDetailsUseCase(movieId) }
    }

    @Test
    fun `when getMovieDetailsUseCase called and load data, isLoaderVisible should be true`() = runTest {
        coEvery { getMovieDetailsUseCase(movieId) } returns Result.success(movieDetailsModel)
        val tested = MovieDetailsViewModel(fragmentArgs.toSavedStateHandle(), getMovieDetailsUseCase, movieDetailsFragmentNavigator)

        tested.isLoaderVisible.value shouldBe true
    }

    @Test
    fun `when getMovieDetailsUseCase called and load throwable, isLoaderVisible should be true`() = runTest {
        coEvery { getMovieDetailsUseCase(movieId) } returns badResponse andThen Result.success(movieDetailsModel)
        every { movieDetailsFragmentNavigator.errorSnackBar(R.string.errorMessageMovies, onAction = any()) } just Runs
        val tested = MovieDetailsViewModel(fragmentArgs.toSavedStateHandle(), getMovieDetailsUseCase, movieDetailsFragmentNavigator)

        tested.isLoaderVisible.value shouldBe true
    }

    @Test
    fun `when getMovieDetailsUseCase called and load data, isScreenElementsVisible should be false`() = runTest {
        coEvery { getMovieDetailsUseCase(movieId) } returns Result.success(movieDetailsModel)
        val tested = MovieDetailsViewModel(fragmentArgs.toSavedStateHandle(), getMovieDetailsUseCase, movieDetailsFragmentNavigator)

        tested.isScreenElementsVisible.value shouldBe false
    }

    @Test
    fun `when getMovieDetailsUseCase called and load throwable, isScreenElementsVisible should be true`() = runTest {
        coEvery { getMovieDetailsUseCase(movieId) } returns badResponse
        every { movieDetailsFragmentNavigator.errorSnackBar(R.string.errorMessageMovies, onAction = any()) } just Runs
        val tested = MovieDetailsViewModel(fragmentArgs.toSavedStateHandle(), getMovieDetailsUseCase, movieDetailsFragmentNavigator)

        tested.isScreenElementsVisible.value shouldBe true
    }
}
