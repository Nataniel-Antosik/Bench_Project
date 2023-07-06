package com.antosik.benchproject.data.popular.movies.entity

import com.antosik.benchproject.data.movies.common.constants.MoviesConstants

internal data class MovieResponseRemote(
    val results: List<MovieRemote>,
) {
    fun toEntity() = results.map {
        MovieEntity(
            it.id,
            it.name,
            it.rating,
            it.releaseDate,
            MoviesConstants.BASE_URL_FOR_IMAGE + it.imagePath
        )
    }
}
