package com.antosik.benchproject.data.movie.details.repo

import com.antosik.benchproject.data.movie.details.entity.GenresEntity
import com.antosik.benchproject.data.movie.details.entity.MovieDetailsEntity
import com.antosik.benchproject.data.movie.details.entity.MovieDetailsRemote
import com.antosik.benchproject.data.movie.details.entity.relations.MovieDetailsWithGenres
import com.antosik.benchproject.data.movie.details.entity.toEntity
import com.antosik.benchproject.data.movies.common.api.MoviesApi
import com.antosik.benchproject.data.movies.common.database.MovieDao
import com.antosik.benchproject.domain.movie.details.entity.GenresModel
import com.antosik.benchproject.domain.movie.details.entity.MovieDetailsModel
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
internal class MovieDetailsDataRepositoryTest {

    val movieDetailsRemote: MovieDetailsRemote = mockk()
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
    val movieDetailsEntity = MovieDetailsEntity(
        54431,
        "https://image.tmdb.org/t/p/w500/something1Background.com",
        "Test1",
        "Example description",
        2000000,
        5.4,
        "2022-02-10",
        "https://image.tmdb.org/t/p/w500/something1.com"
    )
    val genresEntity = listOf(
        GenresEntity(1, "name1"),
        GenresEntity(2, "name1"),
        GenresEntity(3, "name1")
    )
    val apiService: MoviesApi = mockk()
    val dao: MovieDao = mockk(relaxed = true)
    val movieId = 54431
    val tested = MovieDetailsDataRepository(apiService, dao)
    val resultThrowable = Throwable(
        "Unable to resolve host \"api.themoviedb.org\": No address associated with hostname"
    )

    @BeforeEach
    fun beforeEach() {
        mockkStatic(MovieDetailsWithGenres::toDomain)
    }

    @AfterEach
    fun afterEach() {
        unmockkAll()
    }

    @Test
    fun `when method get data from api, it should be mapped to the movie details entity`() = runTest {
        coEvery { apiService.getMovieDetails(movieId) } returns movieDetailsRemote
        coEvery { dao.getMovieDetailsWithGenres(movieId).first().movieDetailsEntity } returns movieDetailsEntity

        dao.getMovieDetailsWithGenres(movieId).first().movieDetailsEntity shouldBeEqualTo movieDetailsEntity
    }

    @Test
    fun `when method get data from api, it should be mapped to the genres entity`() = runTest {
        coEvery { apiService.getMovieDetails(movieId) } returns movieDetailsRemote
        coEvery { dao.getMovieDetailsWithGenres(movieId).first().genresEntity } returns genresEntity

        dao.getMovieDetailsWithGenres(movieId).first().genresEntity shouldBeEqualTo genresEntity
    }

    @Test
    fun `when api returns data, data from database should be mapped to movie details model`() = runTest {
        val movieDetailsWithGenres: MovieDetailsWithGenres = mockk { every { toDomain() } returns movieDetailsModel }

        coEvery { movieDetailsRemote.toEntity() } returns movieDetailsEntity
        coEvery { movieDetailsRemote.genres.toEntity() } returns genresEntity
        coEvery { dao.getMovieDetailsWithGenres(movieId).first() } returns movieDetailsWithGenres

        tested.getMovieDetails(movieId) shouldBeEqualTo movieDetailsModel
    }

    @Test
    fun `when api returns throwable, data from database first time should be null`() = runTest {
        coEvery { apiService.getMovieDetails(movieId) } throws resultThrowable

        tested.getMovieDetails(movieId) shouldBeEqualTo null
    }
}
