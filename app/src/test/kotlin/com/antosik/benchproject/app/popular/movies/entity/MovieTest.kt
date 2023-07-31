package com.antosik.benchproject.app.popular.movies.entity

import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class MovieTest {

    val movieModel = MovieModel(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com", false)
    val movie = Movie(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com", false, 2131034145)
    val moviesModel = listOf(movieModel)
    val movies = listOf(movie)

    @Test
    fun `SHOULD map to Movie WHEN toUi called on MovieModel`() {
        moviesModel.toUi() shouldBeEqualTo movies
    }

    @Test
    fun `SHOULD map to MovieModel WHEN toDomain called on Movie`() {
        movie.toDomain() shouldBeEqualTo movieModel
    }
}
