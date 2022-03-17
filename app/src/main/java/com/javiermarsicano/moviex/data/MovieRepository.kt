package com.javiermarsicano.moviex.data

import com.javiermarsicano.moviex.data.model.MovieResult

interface MovieRepository {

    suspend fun getTopRated(page: Int): List<MovieResult>

}