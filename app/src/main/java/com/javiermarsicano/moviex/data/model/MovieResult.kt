package com.javiermarsicano.moviex.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "movie_result")
data class MovieResult (
    @PrimaryKey val id: Int,
    val backdropPath: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: Date?,
    val title: String?,
    val isVideoProvided: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,
)