package com.antosik.benchproject.data.movies.common.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie")
class MovieEntity {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int = 0

    val name: String

    val rating: Double,

    val releaseDate: String,

    val imagePath: String
}
