package com.antosik.benchproject.data.popular.movies.entity.remote

import com.antosik.benchproject.data.movies.common.constants.MoviesConstants
import com.antosik.benchproject.data.popular.movies.entity.database.MovieEntity

internal data class MovieResponseRemote(
    val results: List<MovieRemote>,
) {
    fun toEntity() = results.map {
        MovieEntity(
            it.id,
            it.name,
            it.rating,
            it.releaseDate,
            MoviesConstants.BASE_URL_FOR_IMAGE + it.imagePath,
            false
        )
    }
}
