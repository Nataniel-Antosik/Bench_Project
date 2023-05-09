package com.antosik.benchproject.data.movie.details.entity

internal data class GenresRemote(
    val id: Int,
    val name: String,
)

internal fun List<GenresRemote>.toEntity() =
    map { GenresEntity(it.id, it.name) }
