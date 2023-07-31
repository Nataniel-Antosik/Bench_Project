package com.antosik.benchproject.data.popular.movies.entity.remote

import com.antosik.benchproject.data.movies.common.constants.MoviesConstants
import com.antosik.benchproject.data.popular.movies.entity.database.MovieEntity
import com.google.gson.annotations.SerializedName

internal data class MovieRemote(
    val id: Int,
    @SerializedName("title")
    val name: String,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val imagePath: String,
) {
    fun toEntity(isFavorite: Boolean?) =
        MovieEntity(
            movieId = id,
            name = name,
            rating = rating,
            releaseDate = releaseDate,
            imagePath = MoviesConstants.BASE_URL_FOR_IMAGE + imagePath,
            isFavorite = isFavorite ?: false
        )
}
