package com.antosik.benchproject.data.movies.common.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.antosik.benchproject.data.movie.details.entity.GenresEntity
import com.antosik.benchproject.data.movie.details.entity.MovieDetailsEntity
import com.antosik.benchproject.data.movie.details.entity.relations.MovieDetailsGenresCrossRef
import com.antosik.benchproject.data.movie.details.entity.relations.MovieDetailsWithGenres
import com.antosik.benchproject.data.popular.movies.entity.MovieEntity

@Dao
internal interface MovieDao {

    @Query("SELECT * FROM MovieEntity")
    suspend fun getMovies(): List<MovieEntity>

    @Transaction
    @Query("SELECT * FROM MovieDetailsEntity WHERE movieId = :movieId")
    suspend fun getMovieDetailsWithGenres(movieId: Int): List<MovieDetailsWithGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(moviesEntity: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(moviesEntity: List<GenresEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(movieDetailsEntity: MovieDetailsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetailsGenresCrossRef(movieDetailsGenresCrossRef: MovieDetailsGenresCrossRef)
}
