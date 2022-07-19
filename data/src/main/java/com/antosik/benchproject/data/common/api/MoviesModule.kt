package com.antosik.benchproject.data.common.api

import com.antosik.benchproject.data.common.constants.Constants
import com.antosik.benchproject.data.movie.details.repo.MovieDetailsDataRepository
import com.antosik.benchproject.data.movies.common.api.MoviesApi
import com.antosik.benchproject.data.popular.movies.repo.PopularMoviesDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal object MoviesModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    @Singleton
    @Provides
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi = retrofit.create()

    @Singleton
    @Provides
    fun providePopularMoviesDataRepository(apiService: MoviesApi) = PopularMoviesDataRepository(apiService)

    @Singleton
    @Provides
    fun provideMovieDetailsDataRepository(apiService: MoviesApi) = MovieDetailsDataRepository(apiService)
}
