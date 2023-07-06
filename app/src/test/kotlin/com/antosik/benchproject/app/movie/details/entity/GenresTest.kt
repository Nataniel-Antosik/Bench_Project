package com.antosik.benchproject.app.movie.details.entity

import com.antosik.benchproject.domain.movie.details.entity.GenresModel
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class GenresTest {

    @Test
    fun `when toUi called on GenresModel should return Genres`() {
        val genresModel = listOf(GenresModel(1, "Test 1"), GenresModel(2, "Test 2"))

        genresModel.toUi() shouldBeEqualTo listOf(Genres(1, "Test 1"), Genres(2, "Test 2"))
    }
}
