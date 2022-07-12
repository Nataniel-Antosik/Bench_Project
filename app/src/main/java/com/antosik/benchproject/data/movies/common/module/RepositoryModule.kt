package com.antosik.benchproject.data.movies.common.module

import com.antosik.benchproject.data.movie.details.repo.MovieDetailsDataRepository
import com.antosik.benchproject.data.popular.movies.repo.PopularMoviesDataRepository
import com.antosik.benchproject.domain.movie.details.repo.MovieDetailsRepository
import com.antosik.benchproject.domain.popular.movies.repo.PopularMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindPopularMoviesRepository(popularMoviesDataRepository: PopularMoviesDataRepository): PopularMoviesRepository

    @Binds
    fun bindMovieDetailsRepository(movieDetailsDataRepository: MovieDetailsDataRepository): MovieDetailsRepository
}
