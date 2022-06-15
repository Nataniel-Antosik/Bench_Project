package com.example.benchproject.data.popular.movies.api

import com.example.benchproject.data.common.constants.Constants
import com.example.benchproject.data.popular.movies.entity.MovieResponseRemote
import retrofit2.http.GET

interface MoviesApi {

    @GET("popular?api_key=" + Constants.API_KEY)
    suspend fun getPopularMovies(): MovieResponseRemote
}
