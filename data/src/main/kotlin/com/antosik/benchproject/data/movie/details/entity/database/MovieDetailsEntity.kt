package com.antosik.benchproject.data.movie.details.entity.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class MovieDetailsEntity(
    @PrimaryKey val movieId: Int,
    val imageBackgroundPath: String,
    val name: String,
    val description: String,
    val budget: Int,
    val rating: Double,
    val releaseDate: String,
    val imagePath: String,
)
