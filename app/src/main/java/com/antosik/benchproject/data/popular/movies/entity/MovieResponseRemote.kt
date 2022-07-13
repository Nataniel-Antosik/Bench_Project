package com.antosik.benchproject.data.popular.movies.entity

import com.antosik.benchproject.data.movies.common.constants.MoviesConstants
import com.antosik.benchproject.domain.popular.movies.entity.MovieModel

data class MovieResponseRemote(
    val results: List<MovieRemote>
) {
    fun toDomain() = results.map {
        MovieModel(
            it.id,
            it.name,
            it.rating,
            it.releaseDate,
            MoviesConstants.BASE_URL_FOR_IMAGE + it.imagePath
        )
    }
}
