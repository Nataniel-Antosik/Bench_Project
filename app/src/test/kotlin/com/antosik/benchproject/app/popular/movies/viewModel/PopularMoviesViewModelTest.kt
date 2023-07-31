package com.antosik.benchproject.app.popular.movies.viewModel

import com.antosik.benchproject.app.common.state.UIState
import com.antosik.benchproject.app.popular.movies.entity.Movie
import com.antosik.benchproject.app.popular.movies.entity.toUi
import com.antosik.benchproject.app.popular.movies.view.navigator.PopularMoviesFragmentNavigator
import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import com.antosik.benchproject.domain.popular.movies.usecase.GetPopularMoviesUseCase
import com.antosik.benchproject.domain.popular.movies.usecase.UpdatePopularMovieUseCase
import com.antosik.benchproject.test.common.LiveDataTest
import io.mockk.Awaits
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@ExtendWith(LiveDataTest::class)
internal class PopularMoviesViewModelTest {

    val movieId = 10000
    val getPopularMoviesUseCase: GetPopularMoviesUseCase = mockk()
    val updatePopularMovieUseCase: UpdatePopularMovieUseCase = mockk()
    val popularMoviesFragmentNavigator: PopularMoviesFragmentNavigator = mockk {
        every { navigateToMovieDetailsFragment(movieId) } just Runs
    }
    val tested by lazy {
        PopularMoviesViewModel(getPopularMoviesUseCase, updatePopularMovieUseCase, popularMoviesFragmentNavigator)
    }

    companion object {
        val moviesModel: List<MovieModel> = mockk(relaxed = true)
        val movies: List<Movie> = mockk()

        @JvmStatic
        fun uiStatesParameters() = listOf(
            Arguments.of(
                moviesModel,
                movies,
                UIState.Success(movies),
            ),
            Arguments.of(
                emptyList<MovieModel>(),
                emptyList<Movie>(),
                UIState.Empty,
            ),
        )
    }

    @BeforeEach
    fun beforeEach() {
        mockkStatic(List<MovieModel>::toUi)
    }

    @AfterEach
    fun afterEach() {
        unmockkAll()
    }

    @ParameterizedTest(name = "SHOULD set {2} state WHEN invoking getPopularMoviesUseCase returns {0}")
    @MethodSource("uiStatesParameters")
    fun `SHOULD set expected state WHEN invoking getPopularMoviesUseCase returns specific value`(
        moviesModel: List<MovieModel>,
        movies: List<Movie>,
        expectedState: UIState,
    ) {
        every { moviesModel.toUi() } returns movies
        coEvery { getPopularMoviesUseCase() } returns moviesModel

        tested.popularMoviesViewState.value shouldBeEqualTo expectedState
    }

    @Test
    fun `SHOULD set Loading state WHEN invoking getPopularMoviesUseCase`() {
        coEvery { getPopularMoviesUseCase() } just Awaits

        tested.popularMoviesViewState.value shouldBeEqualTo UIState.Loading
    }

    @Test
    fun `SHOULD use proper movieId WHEN invoking navigateToMovieDetailsFragment`() {
        coEvery { getPopularMoviesUseCase() } just Awaits

        tested.navigateToMovieDetailsFragment(movieId)

        verify { popularMoviesFragmentNavigator.navigateToMovieDetailsFragment(movieId) }
    }

    @Test
    fun `SHOULD invoke getPopularMoviesUseCase WHEN onRefreshPopularMovies called`() {
        coEvery { getPopularMoviesUseCase() } just Awaits

        tested.onRefreshPopularMovies()

        coVerify { getPopularMoviesUseCase() }
    }

    @Test
    fun `SHOULD invoke updatePopularMovieUseCase WHEN updatePopularMovie called`() {
        coEvery { updatePopularMovieUseCase(any()) } just Runs
        coEvery { getPopularMoviesUseCase() } returns mockk(relaxed = true)

        tested.updatePopularMovie(mockk(relaxed = true))

        coVerify { updatePopularMovieUseCase(any()) }
    }
}
