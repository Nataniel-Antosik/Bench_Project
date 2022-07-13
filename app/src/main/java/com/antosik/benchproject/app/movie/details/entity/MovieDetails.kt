package com.antosik.benchproject.app.movie.details.entity

import com.antosik.benchproject.domain.movie.details.entity.MovieDetailsModel

data class MovieDetails(
    val id: Int,
    val imageBackgroundUrl: String,
    val name: String,
    val genres: List<Genres>,
    val description: String,
    val budget: Int,
    val rating: Double,
    val releaseDate: String,
    val imageUrl: String
) {
    fun budgetText() = if (budget >= 0) "$budget $" else "-"
}

fun MovieDetailsModel.toUi() = MovieDetails(
    id,
    imageBackgroundUrl,
    name,
    genres.toUi(),
    description,
    budget,
    rating,
    releaseDate,
    imageUrl
)
