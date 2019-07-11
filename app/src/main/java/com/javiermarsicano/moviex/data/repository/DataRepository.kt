package com.javiermarsicano.moviex.data.repository

import com.javiermarsicano.moviex.data.models.MovieResult
import io.reactivex.Single

interface DataRepository {

    fun getPopular(query: String): Single<List<MovieResult>>

    fun getTopRatedMovies(query: String): Single<List<MovieResult>>

    fun getUpcomingMovies(query: String): Single<List<MovieResult>>

    fun getCache(): List<MovieResult>

}