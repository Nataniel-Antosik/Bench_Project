package com.example.benchproject.data.common.api

import com.example.benchproject.data.common.constants.Constants
import com.example.benchproject.data.movie.details.repo.MovieDetailsDataRepository
import com.example.benchproject.data.movies.common.api.MoviesApi
import com.example.benchproject.data.popular.movies.repo.PopularMoviesDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    @Singleton
    @Provides
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi = retrofit.create(MoviesApi::class.java)

    @Singleton
    @Provides
    fun providePopularMoviesDataRepository(apiService: MoviesApi) = PopularMoviesDataRepository(apiService)

    @Singleton
    @Provides
    fun provideMovieDetailsDataRepository(apiService: MoviesApi) = MovieDetailsDataRepository(apiService)
}
