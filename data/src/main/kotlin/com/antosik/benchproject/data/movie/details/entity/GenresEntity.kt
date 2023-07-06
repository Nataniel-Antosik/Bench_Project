package com.antosik.benchproject.data.movie.details.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antosik.benchproject.domain.movie.details.entity.GenresModel

@Entity
internal data class GenresEntity(
    @PrimaryKey val genresId: Int,
    val name: String,
)

internal fun List<GenresEntity>.toDomain() =
    map { GenresModel(it.genresId, it.name) }
