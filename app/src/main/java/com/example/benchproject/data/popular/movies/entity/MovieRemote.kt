package com.example.benchproject.data.popular.movies.entity

import com.google.gson.annotations.SerializedName

data class MovieRemote(
    val id: Int,
    @SerializedName("title")
    val name: String,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val imagePath: String
)
