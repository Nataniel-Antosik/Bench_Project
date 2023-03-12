package com.antosik.benchproject.data.movie.details.entity.relations

import com.antosik.benchproject.data.movie.details.entity.GenresEntity
import com.antosik.benchproject.data.movie.details.entity.MovieDetailsEntity
import com.antosik.benchproject.domain.movie.details.entity.GenresModel
import com.antosik.benchproject.domain.movie.details.entity.MovieDetailsModel
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class MovieDetailsWithGenresTest {

    val genresEntity = listOf(
        GenresEntity(1, "Test1"),
        GenresEntity(2, "Test2"),
        GenresEntity(3, "Test3")
    )
    val movieDetailsEntity = MovieDetailsEntity(
        54431,
        "https://image.tmdb.org/t/p/w500/something1Background.com",
        "Test1",
        "Example description",
        2000000,
        5.4,
        "2022-02-10",
        "https://image.tmdb.org/t/p/w500/something1.com"
    )
    val movieDetailsWithGenres = MovieDetailsWithGenres(movieDetailsEntity, genresEntity)
    val movieDetailsModel = MovieDetailsModel(
        54431,
        "https://image.tmdb.org/t/p/w500/something1Background.com",
        "Test1",
        listOf(
            GenresModel(1, "Test1"),
            GenresModel(2, "Test2"),
            GenresModel(3, "Test3")
        ),
        "Example description",
        2000000,
        5.4,
        "2022-02-10",
        "https://image.tmdb.org/t/p/w500/something1.com"
    )

    @Test
    fun `when toDomain called on MovieDetailsWithGenres should return MovieDetailsModel`() {
        movieDetailsWithGenres.toDomain() shouldBeEqualTo movieDetailsModel
    }
}
