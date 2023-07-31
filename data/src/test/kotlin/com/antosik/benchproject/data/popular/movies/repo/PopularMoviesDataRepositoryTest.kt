package com.antosik.benchproject.data.popular.movies.repo

import com.antosik.benchproject.data.movies.common.api.MoviesApi
import com.antosik.benchproject.data.movies.common.database.dao.MovieDao
import com.antosik.benchproject.data.popular.movies.entity.database.MovieEntity
import com.antosik.benchproject.data.popular.movies.entity.database.toDomain
import com.antosik.benchproject.data.popular.movies.entity.database.toEntity
import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

internal class PopularMoviesDataRepositoryTest {

    val apiService: MoviesApi = mockk()
    val dao: MovieDao = mockk(relaxed = true)
    val tested = PopularMoviesDataRepository(apiService, dao)

    @BeforeEach
    fun beforeEach() {
        mockkStatic(List<MovieEntity>::toDomain, MovieModel::toEntity)
    }

    @AfterEach
    fun afterEach() {
        unmockkAll()
    }

    @TestFactory
    fun `SHOULD return expected movies WHEN specific data is present in the database`() =
        listOf(
            emptyList(),
            listOf(mockk<MovieModel>())
        ).map { movies ->
            DynamicTest.dynamicTest("SHOULD return $movies WHEN getPopularMoviesList() was called") {
                runTest {
                    val moviesEntity: List<MovieEntity> = mockk {
                        every { toDomain() } returns movies
                    }

                    coEvery { dao.getMovies() } returns moviesEntity

                    tested.getPopularMoviesList() shouldBeEqualTo movies
                }
            }
        }

    @Test
    fun `SHOULD called updateMovie WHEN updatePopularMovie was invoked`() = runTest {
        val movieEntity = mockk<MovieEntity>()
        val movieModel = mockk<MovieModel> {
            every { toEntity() } returns movieEntity
        }

        tested.updatePopularMovie(movieModel)

        coVerify { dao.updateMovie(movieEntity) }
    }
}
