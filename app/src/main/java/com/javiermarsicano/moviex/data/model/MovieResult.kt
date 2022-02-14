package com.javiermarsicano.moviex.data.model

data class MovieResult (
    val id: Int,
    val backdropPath: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val isVideoProvided: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,
)