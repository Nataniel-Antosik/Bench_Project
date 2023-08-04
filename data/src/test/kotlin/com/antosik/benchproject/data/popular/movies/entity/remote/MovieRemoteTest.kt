package com.antosik.benchproject.data.popular.movies.entity.remote

import com.antosik.benchproject.data.popular.movies.entity.database.MovieEntity
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class MovieRemoteTest {

    @TestFactory
    fun `SHOULD map to MovieEntity WHEN toEntity was called on MovieRemote`() =
        listOf(
            true,
            false,
        ).map { isFavourite ->
            DynamicTest.dynamicTest(
                "SHOULD map to MovieEntity with isFavourite: $isFavourite " +
                    "WHEN toEntity was called on MovieRemote with parameter: $isFavourite"
            ) {
                val movieRemote = MovieRemote(1, "movie", 1.1, "01-01-1999", "Test.pl")
                val movieEntity = MovieEntity(1, "movie", 1.1, "01-01-1999", "https://image.tmdb.org/t/p/w500Test.pl", isFavourite)

                movieRemote.toEntity(isFavourite) shouldBeEqualTo movieEntity
            }
        }
}
