package com.example.benchproject.domain.movie.details.entity

import com.example.benchproject.app.movie.details.entity.Genres

data class GenresModel(
    val id: Int,
    val name: String
)

fun List<GenresModel>.toUi() =
    map { genresModel ->
        Genres(genresModel.id, genresModel.name)
    }
