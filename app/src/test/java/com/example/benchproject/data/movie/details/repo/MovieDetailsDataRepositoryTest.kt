package com.example.benchproject.data.movie.details.repo

import com.example.benchproject.data.movie.details.entity.GenresRemote
import com.example.benchproject.data.movie.details.entity.MovieDetailsRemote
import com.example.benchproject.data.movie.details.entity.toDomain
import com.example.benchproject.data.movies.common.api.MoviesApi
import com.example.benchproject.domain.movie.details.entity.GenresModel
import com.example.benchproject.domain.movie.details.entity.MovieDetailsModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class MovieDetailsDataRepositoryTest {

    private val movieDetailsRemote = MovieDetailsRemote(
        54431,
        "/something1Background.com",
        "Test1",
        listOf(
            GenresRemote(1, "name1"),
            GenresRemote(2, "name2"),
            GenresRemote(3, "name3")
        ),
        "Example description",
        2000000,
        5.4,
        "2022-02-10",
        "/something1.com"
    )
    private val movieDetailsModel = MovieDetailsModel(
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
    private val apiService: MoviesApi = mockk()
    private val resultThrowable = Throwable(
        "Unable to resolve host \"api.themoviedb.org\": No address associated with hostname"
    )
    private val movieId = 54431
    private val tested = MovieDetailsDataRepository(apiService)

    @Test
    fun `when method get data from api, it should be mapped to the movie details model`() = runTest {
        coEvery { apiService.getMovieDetails(movieId) } returns movieDetailsRemote

        tested.getMovieDetails(movieId) shouldBeEqualTo Result.success(movieDetailsModel)
    }

    @Test
    fun `when api returns throwable, 'is failure' data status should be true`() = runTest {
        coEvery { apiService.getMovieDetails(movieId) } throws resultThrowable

        tested.getMovieDetails(movieId).isFailure shouldBe true
    }

    @Test
    fun `when api returns throwable, object should hold exception`() = runTest {
        coEvery { apiService.getMovieDetails(movieId) } throws resultThrowable

        tested.getMovieDetails(movieId).exceptionOrNull() shouldBe resultThrowable
    }

    @Test
    fun `when api returns data, 'is failure' data status shouldn't be true`() = runTest {
        coEvery { apiService.getMovieDetails(movieId) } returns movieDetailsRemote

        tested.getMovieDetails(movieId).isFailure shouldBe false
    }

    @Test
    fun `when repo called method getMovieDetails, it should call method getMovieDetails on API`() = runTest {
        coEvery { apiService.getMovieDetails(movieId) } returns movieDetailsRemote

        tested.getMovieDetails(movieId)

        coVerify { apiService.getMovieDetails(movieId) }
    }

    @Test
    fun `when function get data, it should be mapped to movie details model type`() {
        movieDetailsRemote.toDomain() shouldBeEqualTo movieDetailsModel
    }

    @Test
    fun `when function get data, it should be mapped to genres model type`() {
        val genresRemote = listOf(GenresRemote(1, "Test1"), GenresRemote(2, "Test2"))
        val genresModel = listOf(GenresModel(1, "Test1"), GenresModel(2, "Test2"))

        genresRemote.toDomain() shouldBeEqualTo genresModel
    }
}
