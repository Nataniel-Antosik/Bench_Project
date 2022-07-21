package com.antosik.benchproject.data.movie.details.entity

import com.antosik.benchproject.domain.movie.details.entity.GenresModel

internal data class GenresRemote(
    val id: Int,
    val name: String
)

internal fun List<GenresRemote>.toDomain() = map { GenresModel(it.id, it.name) }
