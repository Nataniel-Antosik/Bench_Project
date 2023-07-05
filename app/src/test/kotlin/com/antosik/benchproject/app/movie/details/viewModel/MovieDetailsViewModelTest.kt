package com.antosik.benchproject.app.movie.details.viewModel

import com.antosik.benchproject.app.common.state.UIState
import com.antosik.benchproject.app.movie.details.entity.MovieDetails
import com.antosik.benchproject.app.movie.details.entity.toUi
import com.antosik.benchproject.app.movie.details.view.MovieDetailsFragmentArgs
import com.antosik.benchproject.domain.movie.details.entity.MovieDetailsModel
import com.antosik.benchproject.domain.movie.details.usecase.GetMovieDetailsUseCase
import com.antosik.benchproject.test.common.LiveDataTest
import io.mockk.Awaits
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(LiveDataTest::class)
internal class MovieDetailsViewModelTest {

    val movieDetailsModel = mockk<MovieDetailsModel>(relaxed = true)
    val movieDetails = mockk<MovieDetails>(relaxed = true)
    val movieId = 54431
    val getMovieDetailsUseCase: GetMovieDetailsUseCase = mockk()
    val fragmentArgs = MovieDetailsFragmentArgs(movieId)
    val tested by lazy {
        MovieDetailsViewModel(fragmentArgs.toSavedStateHandle(), getMovieDetailsUseCase)
    }

    @BeforeEach
    fun beforeEach() {
        mockkStatic(MovieDetailsModel::toUi)
    }

    @AfterEach
    fun afterEach() {
        unmockkAll()
    }

    @Test
    fun `SHOULD set Success state WHEN invoking getMovieDetailsUseCase returns data`() {
        every { movieDetailsModel.toUi() } returns movieDetails
        coEvery { getMovieDetailsUseCase(any()) } returns movieDetailsModel

        tested.movieDetailsViewState.value shouldBeEqualTo UIState.Success(movieDetails)
    }

    @Test
    fun `SHOULD set Loading state WHEN invoking getMovieDetailsUseCase`() {
        coEvery { getMovieDetailsUseCase(any()) } just Awaits

        tested.movieDetailsViewState.value shouldBe UIState.Loading
    }

    @Test
    fun `SHOULD set Loading state WHEN invoking getMovieDetailsUseCase returns null`() {
        coEvery { getMovieDetailsUseCase(any()) } returns null

        tested.movieDetailsViewState.value shouldBe UIState.Empty
    }

    @Test
    fun `SHOULD invoke getMovieDetailsUseCase WHEN onRefreshClick called`() {
        coEvery { getMovieDetailsUseCase(any()) } just Awaits

        tested.onRefreshClick()

        coEvery { getMovieDetailsUseCase(any()) }
    }
}
