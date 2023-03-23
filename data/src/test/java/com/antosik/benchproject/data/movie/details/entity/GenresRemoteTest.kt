package com.antosik.benchproject.data.movie.details.entity

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class GenresRemoteTest {

    @Test
    fun `when toEntity called on GenresRemote should return GenresEntity`() {
        val genresRemote = listOf(GenresRemote(1, "Test1"), GenresRemote(2, "Test2"))

        genresRemote.toEntity() shouldBeEqualTo listOf(GenresEntity(1, "Test1"), GenresEntity(2, "Test2"))
    }
}
