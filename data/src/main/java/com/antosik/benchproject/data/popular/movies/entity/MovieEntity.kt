package com.antosik.benchproject.data.popular.movies.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antosik.benchproject.domain.popular.movies.entity.MovieModel

@Entity
internal data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val rating: Double,
    val releaseDate: String,
    val imagePath: String
)

internal fun List<MovieEntity>.toDomain() = map {
    MovieModel(
        it.id,
        it.name,
        it.rating,
        it.releaseDate,
        it.imagePath
    )
}
