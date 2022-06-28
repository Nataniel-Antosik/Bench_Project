package com.example.benchproject.data.movies.common.api

import com.example.benchproject.data.common.constants.Constants
import com.example.benchproject.data.movie.details.entity.MovieDetailsRemote
import com.example.benchproject.data.popular.movies.entity.MovieResponseRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {

    @GET("popular?api_key=" + Constants.API_KEY)
    suspend fun getPopularMovies(): MovieResponseRemote

    @GET("{movieId}?api_key=" + Constants.API_KEY)
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): MovieDetailsRemote
}