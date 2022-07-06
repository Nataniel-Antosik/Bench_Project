package com.example.benchproject.data.popular.movies.entity

import com.example.benchproject.data.movies.common.constants.MoviesConstants
import com.example.benchproject.domain.popular.movies.entity.MovieModel

data class MovieResponseRemote(
    val results: List<MovieRemote>
) {
    fun toDomain() = results.map { movieRemote ->
        MovieModel(
            movieRemote.id,
            movieRemote.name,
            movieRemote.rating,
            movieRemote.releaseDate,
            MoviesConstants.BASE_URL_FOR_IMAGE + movieRemote.imagePath
        )
    }
}
