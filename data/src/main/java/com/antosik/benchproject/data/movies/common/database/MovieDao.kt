package com.antosik.benchproject.data.movies.common.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.antosik.benchproject.data.popular.movies.entity.MovieEntity

@Dao
internal interface MovieDao {

    @Query("SELECT * FROM MovieEntity")
    suspend fun getMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(moviesEntity: List<MovieEntity>)
}
