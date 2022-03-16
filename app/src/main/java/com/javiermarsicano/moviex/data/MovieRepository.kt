package com.javiermarsicano.moviex.data

import com.javiermarsicano.moviex.data.model.MovieResult
import io.reactivex.Single

interface MovieRepository {

    fun getTopRated(page: Int): Single<List<MovieResult>>

}