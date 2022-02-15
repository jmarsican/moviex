package com.javiermarsicano.moviex.data

import com.javiermarsicano.moviex.data.dto.MovieResultDTO
import com.javiermarsicano.moviex.data.model.MovieResult

const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w92"

fun MovieResultDTO.toModel(): MovieResult {
    return MovieResult(
        this.id,
        this.backdropPath,
        this.originalLanguage,
        this.originalTitle,
        this.overview,
        this.popularity,
        BASE_POSTER_URL + this.posterPath,
        this.releaseDate,
        this.title,
        this.video,
        this.voteAverage,
        this.voteCount
    )
}