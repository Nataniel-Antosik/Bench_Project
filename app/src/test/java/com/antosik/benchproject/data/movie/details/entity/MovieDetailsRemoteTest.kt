package com.antosik.benchproject.data.movie.details.entity

import com.antosik.benchproject.domain.movie.details.entity.GenresModel
import com.antosik.benchproject.domain.movie.details.entity.MovieDetailsModel
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class MovieDetailsRemoteTest {

    val movieDetailsRemote = MovieDetailsRemote(
        54431,
        "/something1Background.com",
        "Test1",
        listOf(
            GenresRemote(1, "name1"),
            GenresRemote(2, "name2"),
            GenresRemote(3, "name3")
        ),
        "Example description",
        2000000,
        5.4,
        "2022-02-10",
        "/something1.com"
    )
    val movieDetailsModel = MovieDetailsModel(
        54431,
        "https://image.tmdb.org/t/p/w500/something1Background.com",
        "Test1",
        listOf(
            GenresModel(1, "name1"),
            GenresModel(2, "name2"),
            GenresModel(3, "name3")
        ),
        "Example description",
        2000000,
        5.4,
        "2022-02-10",
        "https://image.tmdb.org/t/p/w500/something1.com"
    )

    @Test
    fun `when toDomain called on MovieDetailsRemote should return MovieDetailsModel`() {
        movieDetailsRemote.toDomain() shouldBeEqualTo movieDetailsModel
    }
}
