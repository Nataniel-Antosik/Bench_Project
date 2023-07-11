package com.antosik.benchproject.data.movie.details.entity.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.antosik.benchproject.data.movie.details.entity.database.MovieDetailsEntity
import com.antosik.benchproject.data.popular.movies.entity.database.MovieEntity

internal data class MovieAndMovieDetails(
    @Embedded val movieEntity: MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "movieId"
    )
    val movieDetailsEntity: MovieDetailsEntity,
)
