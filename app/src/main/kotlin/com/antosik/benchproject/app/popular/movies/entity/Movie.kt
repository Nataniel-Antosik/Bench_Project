package com.antosik.benchproject.app.popular.movies.entity

import com.antosik.benchproject.R
import com.antosik.benchproject.domain.popular.movies.entity.MovieModel

data class Movie(
    val id: Int,
    val name: String,
    val rating: Double,
    val releaseDate: String,
    val imageUrl: String,
    val isFavourite: Boolean,
    val colorFavourite: Int,
) {
    fun toDomain() =
        MovieModel(
            id,
            name,
            rating,
            releaseDate,
            imageUrl,
            isFavourite,
        )
}

fun List<MovieModel>.toUi() = map {
    Movie(
        it.id,
        it.name,
        it.rating,
        it.releaseDate,
        it.imageUrl,
        it.isFavourite,
        it.isFavourite.changeColorIfFavourite(),
    )
}

private fun Boolean.changeColorIfFavourite() =
    if (this) R.color.red else R.color.black

