package com.antosik.benchproject.data.common.modules.movies

import com.antosik.benchproject.data.favourite.repo.FavouriteMoviesDataRepository
import com.antosik.benchproject.data.movie.details.repo.MovieDetailsDataRepository
import com.antosik.benchproject.data.movies.common.api.MoviesApi
import com.antosik.benchproject.data.movies.common.database.MovieDatabase
import com.antosik.benchproject.data.movies.common.database.dao.MovieDao
import com.antosik.benchproject.data.popular.movies.repo.PopularMoviesDataRepository
import com.antosik.benchproject.domain.favourite.repo.FavouriteMoviesRepository
import com.antosik.benchproject.domain.movie.details.repo.MovieDetailsRepository
import com.antosik.benchproject.domain.popular.movies.repo.PopularMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class MoviesModule {

    @Binds
    abstract fun bindPopularMoviesRepository(implementation: PopularMoviesDataRepository): PopularMoviesRepository

    @Binds
    abstract fun bindMovieDetailsRepository(implementation: MovieDetailsDataRepository): MovieDetailsRepository

    @Binds
    abstract fun bindFavouriteMoviesRepository(implementation: FavouriteMoviesDataRepository): FavouriteMoviesRepository

    companion object {
        @Provides
        fun provideMoviesApi(retrofit: Retrofit): MoviesApi = retrofit.create()

        @Provides
        fun provideMovieDao(database: MovieDatabase): MovieDao =
            database.dao
    }
}
