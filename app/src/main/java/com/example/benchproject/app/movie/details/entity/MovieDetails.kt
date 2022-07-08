package com.example.benchproject.app.movie.details.entity

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
