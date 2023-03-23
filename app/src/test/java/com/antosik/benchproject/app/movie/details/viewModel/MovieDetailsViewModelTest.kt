package com.antosik.benchproject.app.movie.details.viewModel

import com.antosik.benchproject.app.movie.details.entity.Genres
import com.antosik.benchproject.app.movie.details.entity.MovieDetails
import com.antosik.benchproject.app.movie.details.view.MovieDetailsFragmentArgs
import com.antosik.benchproject.domain.movie.details.entity.GenresModel
import com.antosik.benchproject.domain.movie.details.entity.MovieDetailsModel
import com.antosik.benchproject.domain.movie.details.usecase.GetMovieDetailsUseCase
import com.antosik.benchproject.test.common.LiveDataTest
import io.mockk.coEvery
import io.mockk.mockk
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
    val movieId = 54431
    val getMovieDetailsUseCase: GetMovieDetailsUseCase = mockk()
    val fragmentArgs = MovieDetailsFragmentArgs(movieId)

    @Test
    fun `when getMovieDetailsUseCase called and load data, it should be movie details model type`() = runTest {
        coEvery { getMovieDetailsUseCase(any()) } returns movieDetailsModel
        val tested = MovieDetailsViewModel(fragmentArgs.toSavedStateHandle(), getMovieDetailsUseCase)

        tested.responseMovieDetails.value shouldBeEqualTo movieDetails
    }

    @Test
    fun `when getMovieDetailsUseCase called and load data, isLoaderVisible should be false`() = runTest {
        coEvery { getMovieDetailsUseCase(movieId) } returns movieDetailsModel
        val tested = MovieDetailsViewModel(fragmentArgs.toSavedStateHandle(), getMovieDetailsUseCase)

        tested.isLoaderVisible.value shouldBe false
    }

    @Test
    fun `when getMovieDetailsUseCase called and load data, isScreenElementsVisible should be true`() = runTest {
        coEvery { getMovieDetailsUseCase(movieId) } returns movieDetailsModel
        val tested = MovieDetailsViewModel(fragmentArgs.toSavedStateHandle(), getMovieDetailsUseCase)

        tested.isScreenElementsVisible.value shouldBe true
    }
}
