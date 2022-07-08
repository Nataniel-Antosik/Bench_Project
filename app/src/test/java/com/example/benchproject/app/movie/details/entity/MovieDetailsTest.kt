package com.example.benchproject.app.movie.details.entity

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class MovieDetailsTest {

    companion object {
        @JvmStatic
        fun budgets() = listOf(
            Arguments.of(2000000, "2000000 $"),
            Arguments.of(0, "0 $"),
            Arguments.of(-100, "-")
        )
    }

    @ParameterizedTest
    @MethodSource("budgets")
    fun `when budgetText called, it return budget to string with dollar on the end`(input: Int, expected: String) {
        val movieDetails = MovieDetails(
            54431,
            "https://image.tmdb.org/t/p/w500/something1Background.com",
            "Test1",
            listOf(
                Genres(1, "name1"),
                Genres(2, "name2"),
                Genres(3, "name3")
            ),
            "Example description",
            input,
            5.4,
            "2022-02-10",
            "https://image.tmdb.org/t/p/w500/something1.com"
        )

        movieDetails.budgetText() shouldBeEqualTo expected
    }
}
