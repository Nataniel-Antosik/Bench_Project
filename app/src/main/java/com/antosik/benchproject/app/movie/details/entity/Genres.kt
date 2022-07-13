package com.antosik.benchproject.app.movie.details.entity

import com.antosik.benchproject.domain.movie.details.entity.GenresModel

data class Genres(
    val id: Int,
    val name: String
)

fun List<GenresModel>.toUi() = map { Genres(it.id, it.name) }
