package com.antosik.benchproject.data.popular.movies.entity.remote

import com.antosik.benchproject.data.popular.movies.entity.database.MovieEntity
import com.antosik.benchproject.data.popular.movies.entity.database.toDomain
import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class MovieResponseRemoteTest {
    val moviesEntity = listOf(
        MovieEntity(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com", false)
    )
    val moviesModel = listOf(
        MovieModel(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com", false)
    )

    @Test
    fun `SHOULD map to MovieModel WHEN toDomain called on MoviesEntity`() {
        moviesEntity.toDomain() shouldBeEqualTo moviesModel
    }
}
