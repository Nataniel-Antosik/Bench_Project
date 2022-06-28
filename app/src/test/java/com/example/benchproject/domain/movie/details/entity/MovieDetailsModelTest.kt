package com.example.benchproject.domain.movie.details.entity

import com.example.benchproject.app.movie.details.entity.Genres
import com.example.benchproject.app.movie.details.entity.MovieDetails
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class MovieDetailsModelTest {

    private val movieDetailsModel = MovieDetailsModel(
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
    private val movieDetails = MovieDetails(
        54431,
        "https://image.tmdb.org/t/p/w500/something1Background.com",
        "Test1",
        listOf(
            Genres(1, "name1"),
            Genres(2, "name2"),
            Genres(3, "name3")
        ),
        "Example description",
        2000000,
        5.4,
        "2022-02-10",
        "https://image.tmdb.org/t/p/w500/something1.com"
    )

    @Test
    fun `when mapToUI called on MovieDetailsModel should return MovieDetails`() {
        movieDetailsModel.toUi() shouldBeEqualTo movieDetails
    }
}
