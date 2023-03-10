package com.antosik.benchproject.data.movie.details.entity

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class GenresRemoteTest {

    @Test
    fun `when toEntity called on GenresRemote should return GenresEntity`() {
        val movieId = 860231
        val genresRemote = listOf(GenresRemote(1, "Test1"), GenresRemote(2, "Test2"))

        genresRemote.toEntity(movieId) shouldBeEqualTo listOf(GenresEntity(1, "Test1", 860231), GenresEntity(2, "Test2", 860231))
    }
}
