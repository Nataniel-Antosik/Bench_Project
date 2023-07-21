package com.antosik.benchproject.data.popular.movies.entity.database

import com.antosik.benchproject.domain.popular.movies.entity.MovieModel
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class MovieEntityTest {

    val movieEntity = MovieEntity(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com", false)
    val movieModel = MovieModel(10001, "Test1", 5.4, "2022-06-23", "https://image.tmdb.org/t/p/w500/something1.com", false)
    val moviesEntity = listOf(movieEntity)
    val moviesModel = listOf(movieModel)

    @Test
    fun `SHOULD return MovieModel WHEN toDomain called on MoviesEntity`() {
        moviesEntity.toDomain() shouldBeEqualTo moviesModel
    }

    @Test
    fun `SHOULD map to MovieEntity WHEN toEntity called on MovieResponseRemote`() {
        movieModel.toEntity() shouldBeEqualTo movieEntity
    }
}
