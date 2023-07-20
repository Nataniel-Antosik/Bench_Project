package com.antosik.benchproject.data.popular.movies.repo

import com.antosik.benchproject.data.movies.common.api.MoviesApi
import com.antosik.benchproject.data.movies.common.database.dao.MovieDao
import com.antosik.benchproject.data.popular.movies.entity.database.MovieEntity
import com.antosik.benchproject.data.popular.movies.entity.database.toDomain
import com.antosik.benchproject.data.popular.movies.entity.remote.MovieResponseRemote
import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PopularMoviesDataRepositoryTest {

    val moviesEntity = listOf(
        MovieEntity(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com", false),
    )
    val moviesModel = listOf(
        MovieModel(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com", false),
    )
    val movieResponseRemote: MovieResponseRemote = mockk()
    val apiService: MoviesApi = mockk()
    val dao: MovieDao = mockk(relaxed = true)
    val tested = PopularMoviesDataRepository(apiService, dao)
    val resultThrowable = Throwable(
        "Unable to resolve host \"api.themoviedb.org\": No address associated with hostname"
    )

    @BeforeEach
    fun beforeEach() {
        mockkStatic(List<MovieEntity>::toDomain)
    }

    @AfterEach
    fun afterEach() {
        unmockkAll()
    }

    @Test
    fun `SHOULD map to the MovieEntity type WHEN method get data from api`() =
        runTest {
            coEvery { apiService.getPopularMovies(any()) } returns movieResponseRemote
            coEvery { dao.getMovies() } returns moviesEntity

            dao.getMovies() shouldBeEqualTo moviesEntity
        }

    @Test
    fun `SHOULD map to the MovieModel WHEN api returns data`() = runTest {
        val movies: List<MovieEntity> = mockk {
            every { toDomain() } returns moviesModel
        }

        coEvery { movieResponseRemote.toEntity() } returns moviesEntity
        coEvery { apiService.getPopularMovies(any()) } returns movieResponseRemote
        coEvery { dao.getMovies() } returns movies

        tested.getPopularMoviesList() shouldBeEqualTo moviesModel
    }

    @Test
    fun `SHOULD return empty list WHEND api returns throwable`() = runTest {
        val movies: List<MovieEntity> = mockk {
            every { toDomain() } returns emptyList()
        }

        coEvery { apiService.getPopularMovies(any()) } throws resultThrowable
        coEvery { dao.getMovies() } returns movies

        tested.getPopularMoviesList() shouldBeEqualTo emptyList()
    }
}
