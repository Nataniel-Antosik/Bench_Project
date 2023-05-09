package com.antosik.benchproject.data.movie.details.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.antosik.benchproject.data.movie.details.entity.MovieDetailsEntity
import com.antosik.benchproject.data.popular.movies.entity.MovieEntity

internal data class MovieAndMovieDetails(
    @Embedded val movieEntity: MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "movieId"
    )
    val movieDetailsEntity: MovieDetailsEntity,
)
