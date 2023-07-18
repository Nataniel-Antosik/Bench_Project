package com.antosik.benchproject.app.popular.movies.entity

import com.antosik.benchproject.R
import com.antosik.benchproject.domain.popular.movies.entity.MovieModel

data class Movie(
    val id: Int,
    val name: String,
    val rating: Double,
    val releaseDate: String,
    val imageUrl: String,
    val isFavorite: Boolean,
    val colorFavorite: Int,
)

fun List<MovieModel>.toUi() = map {
    Movie(
        it.id,
        it.name,
        it.rating,
        it.releaseDate,
        it.imageUrl,
        it.isFavorite,
        it.isFavorite.changeColorIfFavorite(),
    )
}

private fun Boolean.changeColorIfFavorite() =
    if (this) R.color.red else R.color.black

