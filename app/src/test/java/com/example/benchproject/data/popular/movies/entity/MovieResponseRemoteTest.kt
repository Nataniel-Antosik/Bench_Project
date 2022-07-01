package com.example.benchproject.data.popular.movies.entity

import com.example.benchproject.domain.popular.movies.entity.MovieModel
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class MovieResponseRemoteTest {
    val moviesRemote = listOf(
        MovieRemote(10001, "Test1", 5.4, "2022-06-23", "/something1.com"),
        MovieRemote(10002, "Test2", 6.4, "2022-06-22", "/something2.com"),
        MovieRemote(10003, "Test3", 3.4, "2022-06-21", "/something3.com")
    )
    val moviesModel = listOf(
        MovieModel(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com"),
        MovieModel(10002, "Test2", 6.4, "2022-06-22", "https://image.tmdb.org/t/p/w500/something2.com"),
        MovieModel(10003, "Test3", 3.4, "2022-06-21", "https://image.tmdb.org/t/p/w500/something3.com")
    )
    val movieResponseRemote = MovieResponseRemote(moviesRemote)

    @Test
    fun `when toDomain called on MovieResponseRemote should return MovieModel`() {
        movieResponseRemote.toDomain() shouldBeEqualTo moviesModel
    }
}
