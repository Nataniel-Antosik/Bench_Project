package com.antosik.benchproject.domain.popular.movies.entity

import com.antosik.benchproject.app.popular.movies.entity.Movie

data class MovieModel(
    val id: Int,
    val name: String,
    val rating: Double,
    val releaseDate: String,
    val imageUrl: String
)
fun List<MovieModel>.toUi() =
    map { movieModel ->
        Movie(
            movieModel.id,
            movieModel.name,
            movieModel.rating,
            movieModel.releaseDate,
            movieModel.imageUrl
        )
    }
