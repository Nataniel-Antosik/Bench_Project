package com.antosik.benchproject.data.popular.movies.entity.remote

import com.antosik.benchproject.data.popular.movies.entity.database.MovieEntity
import com.antosik.benchproject.data.popular.movies.entity.database.toDomain
import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class MovieResponseRemoteTest {
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

    @Test
    fun `when toDomain called on moviesEntity should return MovieModel`() {
        moviesEntity.toDomain() shouldBeEqualTo moviesModel
    }
}
