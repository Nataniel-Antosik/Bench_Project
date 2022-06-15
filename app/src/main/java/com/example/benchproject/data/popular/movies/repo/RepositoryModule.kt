package com.example.benchproject.data.popular.movies.repo

import com.example.benchproject.domain.popular.movies.repo.PopularMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindPopularMoviesRepository(impl: PopularMoviesDataRepository): PopularMoviesRepository
}
