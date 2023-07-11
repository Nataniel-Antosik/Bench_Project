package com.antosik.benchproject.data.movie.details.entity.database.relations

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "genresId"])
data class MovieDetailsGenresCrossRef(
    val movieId: Int,
    val genresId: Int,
)
