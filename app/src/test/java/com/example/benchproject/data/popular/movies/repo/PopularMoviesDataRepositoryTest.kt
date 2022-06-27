package com.example.benchproject.data.popular.movies.repo

import com.example.benchproject.data.popular.movies.api.MoviesApi
import com.example.benchproject.data.popular.movies.entity.MovieRemote
import com.example.benchproject.data.popular.movies.entity.MovieResponseRemote
import com.example.benchproject.domain.popular.movies.entity.MovieModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class PopularMoviesDataRepositoryTest {

    private val movieListRemote = listOf(
        MovieRemote(10001, "Test1", 5.4, "2022-06-23", "https:/something1.com"),
        MovieRemote(10002, "Test2", 6.4, "2022-06-22", "https:/something2.com"),
        MovieRemote(10003, "Test3", 3.4, "2022-06-21", "https:/something3.com")
    )
    private val movieListModel = Result.success(
        listOf(
            MovieModel(10001, "Test1", 5.4, "2022-06-23", "https:/something1.com"),
            MovieModel(10002, "Test2", 6.4, "2022-06-22", "https:/something2.com"),
            MovieModel(10003, "Test3", 3.4, "2022-06-21", "https:/something3.com")
        )
    )
    private val movieResponseRemote = MovieResponseRemote(movieListRemote)
    private val apiService: MoviesApi = mockk()
    private val tested = PopularMoviesDataRepository(apiService)
    private val resultThrowable = Throwable(
        "Unable to resolve host \"api.themoviedb.org\": No address associated with hostname"
    )
    @Test
    fun `when method get data from api, they should be mapped to the movie model type`() = runTest {
        coEvery { apiService.getPopularMovies() } returns movieResponseRemote

        tested.getPopularMoviesList() shouldBeEqualTo movieListModel
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
}
