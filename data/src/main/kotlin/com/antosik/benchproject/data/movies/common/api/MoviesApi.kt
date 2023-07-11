package com.antosik.benchproject.data.movies.common.api

import com.antosik.benchproject.data.movie.details.entity.remote.MovieDetailsRemote
import com.antosik.benchproject.data.popular.movies.entity.remote.MovieResponseRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MoviesApi {

    @GET("popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieResponseRemote

    @GET("{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String): MovieDetailsRemote
}
