package com.antosik.benchproject.data.movies.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antosik.benchproject.data.popular.movies.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class MovieDatabase : RoomDatabase() {

    abstract val dao: MovieDao
}
