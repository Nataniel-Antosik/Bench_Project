package com.example.benchproject.data.popular.movies.entity

import com.google.gson.annotations.SerializedName

data class MovieRemote(
    @SerializedName("title")
    val name: String,
    @SerializedName("vote_average")
    val rating: Double
)
