package com.antosik.benchproject.data.popular.movies.repo

import com.antosik.benchproject.data.movies.common.api.MoviesApi
import com.antosik.benchproject.data.popular.movies.entity.MovieRemote
import com.antosik.benchproject.data.popular.movies.entity.MovieResponseRemote
import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class PopularMoviesDataRepositoryTest {

    val moviesRemote = listOf(
        MovieRemote(10001, "Test1", 5.4, "2022-06-23", "/something1.com"),
        MovieRemote(10002, "Test2", 6.4, "2022-06-22", "/something2.com"),
        MovieRemote(10003, "Test3", 3.4, "2022-06-21", "/something3.com")
    )
    val moviesModel = listOf(
        MovieModel(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com"),
        MovieModel(10002, "Test2", 6.4, "2022-06-22", "https://image.tmdb.org/t/p/w500/something2.com"),
        MovieModel(10003, "Test3", 3.4, "2022-06-21", "https://image.tmdb.org/t/p/w500/something3.com")
    )
    val movieResponseRemote = MovieResponseRemote(moviesRemote)
    val apiService: MoviesApi = mockk()
    val tested = PopularMoviesDataRepository(apiService)
    val resultThrowable = Throwable(
        "Unable to resolve host \"api.themoviedb.org\": No address associated with hostname"
    )
    @Test
    fun `when method get data from api, they should be mapped to the movie model type`() = runTest {
        coEvery { apiService.getPopularMovies() } returns movieResponseRemote

        tested.getPopularMoviesList() shouldBeEqualTo Result.success(moviesModel)
    }

    @Test
    fun `when api returns throwable, 'is failure' data status should be true`() = runTest {
        coEvery { apiService.getPopularMovies() } throws resultThrowable

        tested.getPopularMoviesList().isFailure shouldBe true
    }

    @Test
    fun `when api returns throwable, object should hold exception`() = runTest {
        coEvery { apiService.getPopularMovies() } throws resultThrowable

        tested.getPopularMoviesList().exceptionOrNull() shouldBe resultThrowable
    }

    @Test
    fun `when api returns data, 'is failure' data status shouldn't be true`() = runTest {
        coEvery { apiService.getPopularMovies() } returns movieResponseRemote

        tested.getPopularMoviesList().isFailure shouldBe false
    }

    @Test
    fun `when repo called method getPopularMovies, it should call method getPopularMovies on API`() = runTest {
        coEvery { apiService.getPopularMovies() } returns movieResponseRemote

        apiService.getPopularMovies()

        coVerify { apiService.getPopularMovies() }
    }
}
