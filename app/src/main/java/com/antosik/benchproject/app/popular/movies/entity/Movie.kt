package com.antosik.benchproject.app.popular.movies.entity

data class Movie(
    val id: Int,
    val name: String,
    val rating: Double,
    val releaseDate: String,
    val imageUrl: String
)
