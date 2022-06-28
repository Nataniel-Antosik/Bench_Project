package com.example.benchproject.data.movie.details.entity

import com.example.benchproject.domain.movie.details.entity.GenresModel

data class GenresRemote(
    val id: Int,
    val name: String
)

fun List<GenresRemote>.toDomain() =
    this.map { genresRemote ->
        GenresModel(genresRemote.id, genresRemote.name)
    }
