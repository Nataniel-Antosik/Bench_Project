package com.antosik.benchproject.data.movie.details.entity

import com.antosik.benchproject.domain.movie.details.entity.GenresModel
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class GenresRemoteTest {

    @Test
    fun `when toDomain called on GenresRemote should return GenresModel`() {
        val genresRemote = listOf(GenresRemote(1, "Test1"), GenresRemote(2, "Test2"))

        genresRemote.toDomain() shouldBeEqualTo listOf(GenresModel(1, "Test1"), GenresModel(2, "Test2"))
    }
}
