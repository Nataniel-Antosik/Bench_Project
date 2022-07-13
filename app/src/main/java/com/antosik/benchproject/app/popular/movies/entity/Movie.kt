package com.antosik.benchproject.app.popular.movies.entity

import com.antosik.benchproject.domain.popular.movies.entity.MovieModel

data class Movie(
    val id: Int,
    val name: String,
    val rating: Double,
    val releaseDate: String,
    val imageUrl: String
)

fun List<MovieModel>.toUi() = map { Movie(it.id, it.name, it.rating, it.releaseDate, it.imageUrl) }
