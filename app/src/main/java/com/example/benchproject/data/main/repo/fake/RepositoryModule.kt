package com.example.benchproject.data.main.repo.fake

import com.example.benchproject.domain.main.repo.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideMainRepository(impl: FakeMainRepository): MainRepository
}
