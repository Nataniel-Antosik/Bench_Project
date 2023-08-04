package com.antosik.benchproject.data.popular.movies.entity.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antosik.benchproject.domain.popular.movies.entity.MovieModel

@Entity
internal data class MovieEntity(
    @PrimaryKey
    val movieId: Int,
    val name: String,
    val rating: Double,
    val releaseDate: String,
    val imagePath: String,
    val isFavourite: Boolean,
)

internal fun List<MovieEntity>.toDomain() = map {
    MovieModel(
        it.movieId,
        it.name,
        it.rating,
        it.releaseDate,
        it.imagePath,
        it.isFavourite,
    )
}

internal fun MovieModel.toEntity() =
    MovieEntity(
        id,
        name,
        rating,
        releaseDate,
        imageUrl,
        isFavourite,
    )
