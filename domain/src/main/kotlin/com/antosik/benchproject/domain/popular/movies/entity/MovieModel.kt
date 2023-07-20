package com.antosik.benchproject.domain.popular.movies.entity

data class MovieModel(
    val id: Int,
    val name: String,
    val rating: Double,
    val releaseDate: String,
    val imageUrl: String,
    val isFavorite: Boolean,
)
