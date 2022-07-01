package com.example.benchproject.data.movies.common.module

import com.example.benchproject.data.movie.details.repo.MovieDetailsDataRepository
import com.example.benchproject.data.popular.movies.repo.PopularMoviesDataRepository
import com.example.benchproject.domain.movie.details.repo.MovieDetailsRepository
import com.example.benchproject.domain.popular.movies.repo.PopularMoviesRepository
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
