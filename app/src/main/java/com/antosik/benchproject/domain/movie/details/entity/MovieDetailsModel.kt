package com.antosik.benchproject.domain.movie.details.entity

import com.antosik.benchproject.app.movie.details.entity.MovieDetails

data class MovieDetailsModel(
    val id: Int,
    val imageBackgroundUrl: String,
    val name: String,
    val genres: List<GenresModel>,
    val description: String,
    val budget: Int,
    val rating: Double,
    val releaseDate: String,
    val imageUrl: String
) {
    fun toUi() = MovieDetails(
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
}
