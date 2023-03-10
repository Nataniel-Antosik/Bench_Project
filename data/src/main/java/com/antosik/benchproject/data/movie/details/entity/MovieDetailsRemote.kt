package com.antosik.benchproject.data.movie.details.entity

import com.antosik.benchproject.data.movies.common.constants.MoviesConstants
import com.google.gson.annotations.SerializedName

internal data class MovieDetailsRemote(
    val id: Int,
    @SerializedName("backdrop_path")
    val imageBackgroundPath: String,
    @SerializedName("title")
    val name: String,
    @SerializedName("genres")
    val genres: List<GenresRemote>,
    @SerializedName("overview")
    val description: String,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val imagePath: String
) {
    fun toEntity() = MovieDetailsEntity(
        id,
        MoviesConstants.BASE_URL_FOR_IMAGE + imageBackgroundPath,
        name,
        description,
        budget,
        rating,
        releaseDate,
        MoviesConstants.BASE_URL_FOR_IMAGE + imagePath
    )
}
