package com.antosik.benchproject.data.popular.movies.repo

import com.antosik.benchproject.data.movies.common.api.MoviesApi
import com.antosik.benchproject.data.movies.common.database.MovieDao
import com.antosik.benchproject.data.popular.movies.entity.MovieEntity
import com.antosik.benchproject.data.popular.movies.entity.MovieResponseRemote
import com.antosik.benchproject.data.popular.movies.entity.toDomain
import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class PopularMoviesDataRepositoryTest {

    val moviesEntity = listOf(
        MovieEntity(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com"),
        MovieEntity(10002, "Test2", 6.4, "2022-06-22", "https://image.tmdb.org/t/p/w500/something2.com"),
        MovieEntity(10003, "Test3", 3.4, "2022-06-21", "https://image.tmdb.org/t/p/w500/something3.com")
    )
    val moviesModel = listOf(
        MovieModel(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com"),
        MovieModel(10002, "Test2", 6.4, "2022-06-22", "https://image.tmdb.org/t/p/w500/something2.com"),
        MovieModel(10003, "Test3", 3.4, "2022-06-21", "https://image.tmdb.org/t/p/w500/something3.com")
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
    fun `when method get data from api, it should be mapped to the movie entity type`() =
        runTest {
            coEvery { apiService.getPopularMovies(any()) } returns movieResponseRemote
            coEvery { dao.getMovies() } returns moviesEntity

            dao.getMovies() shouldBeEqualTo moviesEntity
        }

    @Test
    fun `when api returns data, data from database should be mapped to movie model`() = runTest {
        val movies: List<MovieEntity> = mockk {
            every { toDomain() } returns moviesModel
        }

        coEvery { movieResponseRemote.toEntity() } returns moviesEntity
        coEvery { apiService.getPopularMovies(any()) } returns movieResponseRemote
        coEvery { dao.getMovies() } returns movies

        tested.getPopularMoviesList() shouldBeEqualTo moviesModel
    }

    @Test
    fun `when api returns throwable, data from database first time should be empty`() = runTest {
        val movies: List<MovieEntity> = mockk {
            every { toDomain() } returns emptyList()
        }

        coEvery { apiService.getPopularMovies(any()) } throws resultThrowable
        coEvery { dao.getMovies() } returns movies

        tested.getPopularMoviesList() shouldBeEqualTo emptyList()
    }
}
