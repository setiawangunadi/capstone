package com.setiawan.capstone.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "overview") val overview: String,

    @ColumnInfo(name = "originalLanguage") val originalLanguage: String,

    @ColumnInfo(name = "originalTitle") val originalTitle: String,

    @ColumnInfo(name = "video") val video: Boolean,

    @ColumnInfo(name = "title") val title: String,

    @ColumnInfo(name = "genre_ids") val genreIds: List<Int>,

    @ColumnInfo(name = "poster_path") val posterPath: String,

    @ColumnInfo(name = "backdrop_path") val backdropPath: String,

    @ColumnInfo(name = "release_date") val releaseDate: String,

    @ColumnInfo(name = "popularity") val popularity: Float,

    @ColumnInfo(name = "vote_average") val voteAverage: Float,

    @ColumnInfo(name = "adult") var adult: Boolean,

    @ColumnInfo(name = "vote_count") val voteCount: Int,

    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean = false

)