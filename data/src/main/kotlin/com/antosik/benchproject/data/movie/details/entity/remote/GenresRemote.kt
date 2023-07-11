package com.antosik.benchproject.data.movie.details.entity.remote

import com.antosik.benchproject.data.movie.details.entity.database.GenresEntity

internal data class GenresRemote(
    val id: Int,
    val name: String,
)

internal fun List<GenresRemote>.toEntity() =
    map { GenresEntity(it.id, it.name) }
