package com.antosik.benchproject.data.common.modules

import android.app.Application
import androidx.room.Room
import com.antosik.benchproject.data.common.constants.Constants
import com.antosik.benchproject.data.movie.details.repo.MovieDetailsDataRepository
import com.antosik.benchproject.data.movies.common.api.MoviesApi
import com.antosik.benchproject.data.movies.common.database.MovieDatabase
import com.antosik.benchproject.data.popular.movies.repo.PopularMoviesDataRepository
import com.antosik.benchproject.domain.movie.details.repo.MovieDetailsRepository
import com.antosik.benchproject.domain.popular.movies.repo.PopularMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
object MoviesModule {

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    @Module
    @InstallIn(ViewModelComponent::class)
    internal object Providers {

        @Provides
        fun provideMoviesApi(retrofit: Retrofit): MoviesApi = retrofit.create()

        @Provides
        fun providePopularMoviesRepository(apiService: MoviesApi): PopularMoviesRepository = PopularMoviesDataRepository(apiService)

        @Provides
        fun provideMovieDetailsRepository(apiService: MoviesApi): MovieDetailsRepository = MovieDetailsDataRepository(apiService)

        @Provides
        fun provideMovieDatabase(app: Application): MovieDatabase =
            Room.databaseBuilder(app, MovieDatabase::class.java, "movie_database").build()
    }
}
