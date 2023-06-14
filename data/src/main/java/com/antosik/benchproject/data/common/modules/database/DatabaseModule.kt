package com.antosik.benchproject.data.common.modules.database

import android.app.Application
import androidx.room.Room
import com.antosik.benchproject.data.movies.common.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object DatabaseModule {

    @Provides
    fun provideMovieDatabase(app: Application): MovieDatabase =
        Room.databaseBuilder(app, MovieDatabase::class.java, "movie_database")
            .fallbackToDestructiveMigration()
            .build()
}
