package com.example.benchproject.domain.popular.movies.entity

import com.example.benchproject.app.popular.movies.entity.Movie
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class MovieModelTest {

    val moviesModel = listOf(
        MovieModel(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com"),
        MovieModel(10002, "Test2", 6.4, "2022-06-22", "https://image.tmdb.org/t/p/w500/something2.com"),
        MovieModel(10003, "Test3", 3.4, "2022-06-21", "https://image.tmdb.org/t/p/w500/something3.com")
    )
    val movies = listOf(
        Movie(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com"),
        Movie(10002, "Test2", 6.4, "2022-06-22", "https://image.tmdb.org/t/p/w500/something2.com"),
        Movie(10003, "Test3", 3.4, "2022-06-21", "https://image.tmdb.org/t/p/w500/something3.com")
    )

    @Test
    fun `when mapToUI called on MovieModel should return Movie`() {
        moviesModel.toUi() shouldBeEqualTo movies
    }
}
