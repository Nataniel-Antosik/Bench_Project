package com.antosik.benchproject.data.favourite.repo

import com.antosik.benchproject.data.movies.common.database.dao.MovieDao
import com.antosik.benchproject.data.popular.movies.entity.database.MovieEntity
import com.antosik.benchproject.data.popular.movies.entity.database.toDomain
import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class FavouriteMoviesDataRepositoryTest {

    val dao: MovieDao = mockk()
    val tested = FavouriteMoviesDataRepository(dao)

    @BeforeEach
    fun beforeEach() {
        mockkStatic(List<MovieEntity>::toDomain)
    }

    @AfterEach
    fun afterEach() {
        unmockkAll()
    }

    @Test
    fun `SHOULD return favourite movies WHEN data is present in the database`() = runTest {
        val movies: List<MovieModel> = mockk()
        val moviesEntity: List<MovieEntity> = mockk {
            every { toDomain() } returns movies
        }
        coEvery { dao.getFavouriteMovies() } returns flowOf(moviesEntity)

        tested.getFavouriteMovies().first() shouldBeEqualTo movies
    }
}
