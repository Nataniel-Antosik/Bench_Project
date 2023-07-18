package com.antosik.benchproject.data.popular.movies.entity.database

import com.antosik.benchproject.data.popular.movies.entity.remote.MovieRemote
import com.antosik.benchproject.data.popular.movies.entity.remote.MovieResponseRemote
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class MovieEntityTest {
    val moviesRemote = listOf(
        MovieRemote(10001, "Test1", 5.4, "2022-06-23", "/something1.com")
    )
    val moviesEntity = listOf(
        MovieEntity(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com", false)
    )
    val movieResponseRemote = MovieResponseRemote(moviesRemote)

    @Test
    fun `SHOULD map to MovieEntity WHEN toEntity called on MovieResponseRemote`() {
        movieResponseRemote.toEntity() shouldBeEqualTo moviesEntity
    }
}
