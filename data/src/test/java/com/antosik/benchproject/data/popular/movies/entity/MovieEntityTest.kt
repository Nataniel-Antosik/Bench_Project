package com.antosik.benchproject.data.popular.movies.entity

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class MovieEntityTest {
    val moviesRemote = listOf(
        MovieRemote(10001, "Test1", 5.4, "2022-06-23", "/something1.com"),
        MovieRemote(10002, "Test2", 6.4, "2022-06-22", "/something2.com"),
        MovieRemote(10003, "Test3", 3.4, "2022-06-21", "/something3.com")
    )
    val moviesEntity = listOf(
        MovieEntity(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com"),
        MovieEntity(10002, "Test2", 6.4, "2022-06-22", "https://image.tmdb.org/t/p/w500/something2.com"),
        MovieEntity(10003, "Test3", 3.4, "2022-06-21", "https://image.tmdb.org/t/p/w500/something3.com")
    )
    val movieResponseRemote = MovieResponseRemote(moviesRemote)

    @Test
    fun `when toEntity called on MovieResponseRemote should return MovieEntity`() {
        movieResponseRemote.toEntity() shouldBeEqualTo moviesEntity
    }
}
