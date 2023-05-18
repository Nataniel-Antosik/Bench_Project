package com.antosik.benchproject.data.movies.common.api

import com.antosik.benchproject.data.common.constants.Constants
import com.antosik.benchproject.data.movie.details.entity.MovieDetailsRemote
import com.antosik.benchproject.data.popular.movies.entity.MovieResponseRemote
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MoviesApi {

    // TODO Use `@Query` instead of adding query parameter by yourself
    @GET("popular?api_key=" + Constants.API_KEY)
    suspend fun getPopularMovies(): MovieResponseRemote

    @GET("{movieId}?api_key=" + Constants.API_KEY)
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): MovieDetailsRemote
}
