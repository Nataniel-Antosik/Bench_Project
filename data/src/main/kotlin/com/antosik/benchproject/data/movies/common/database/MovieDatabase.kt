package com.antosik.benchproject.data.movies.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antosik.benchproject.data.movie.details.entity.database.GenresEntity
import com.antosik.benchproject.data.movie.details.entity.database.MovieDetailsEntity
import com.antosik.benchproject.data.movie.details.entity.database.relations.MovieDetailsGenresCrossRef
import com.antosik.benchproject.data.movies.common.database.dao.MovieDao
import com.antosik.benchproject.data.popular.movies.entity.database.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
        MovieDetailsEntity::class,
        GenresEntity::class,
        MovieDetailsGenresCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
internal abstract class MovieDatabase : RoomDatabase() {

    abstract val dao: MovieDao
}
