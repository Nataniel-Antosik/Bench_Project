package com.antosik.benchproject.data.common.modules.movies

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
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
internal object MoviesModule {

    @Provides
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi = retrofit.create()

    @Provides
    fun providePopularMoviesRepository(apiService: MoviesApi, database: MovieDatabase): PopularMoviesRepository =
        PopularMoviesDataRepository(apiService, database.dao)

    @Provides
    fun provideMovieDetailsRepository(apiService: MoviesApi, database: MovieDatabase): MovieDetailsRepository =
        MovieDetailsDataRepository(apiService, database.dao)
}
