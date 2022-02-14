package com.javiermarsicano.moviex.data

import com.javiermarsicano.moviex.data.dto.MovieResultDTO
import com.javiermarsicano.moviex.data.model.MovieResult

fun MovieResultDTO.toModel(): MovieResult {
    return MovieResult(
        this.id,
        this.backdropPath,
        this.originalLanguage,
        this.originalTitle,
        this.overview,
        this.popularity,
        this.posterPath,
        this.releaseDate,
        this.title,
        this.video,
        this.voteAverage,
        this.voteCount
    )
}