package com.javiermarsicano.moviex.data

import com.javiermarsicano.moviex.data.model.MovieResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getTopRated(page: Int): Flow<List<MovieResult>>

}