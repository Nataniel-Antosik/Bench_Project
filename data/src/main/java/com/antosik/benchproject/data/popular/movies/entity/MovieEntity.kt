package com.antosik.benchproject.data.popular.movies.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val rating: Double,
    val releaseDate: String,
    val imagePath: String
)
