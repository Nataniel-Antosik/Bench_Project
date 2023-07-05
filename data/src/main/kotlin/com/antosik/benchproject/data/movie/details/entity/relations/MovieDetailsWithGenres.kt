package com.antosik.benchproject.data.movie.details.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.antosik.benchproject.data.movie.details.entity.GenresEntity
import com.antosik.benchproject.data.movie.details.entity.MovieDetailsEntity
import com.antosik.benchproject.data.movie.details.entity.toDomain
import com.antosik.benchproject.domain.movie.details.entity.MovieDetailsModel

internal data class MovieDetailsWithGenres(
    @Embedded val movieDetailsEntity: MovieDetailsEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "genresId",
        associateBy = Junction(MovieDetailsGenresCrossRef::class)
    )
    val genresEntity: List<GenresEntity>,
) {
    fun toDomain() = MovieDetailsModel(
        movieDetailsEntity.movieId,
        movieDetailsEntity.imageBackgroundPath,
        movieDetailsEntity.name,
        genresEntity.toDomain(),
        movieDetailsEntity.description,
        movieDetailsEntity.budget,
        movieDetailsEntity.rating,
        movieDetailsEntity.releaseDate,
        movieDetailsEntity.imagePath
    )
}
