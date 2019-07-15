package com.javiermarsicano.moviex.data.repository

import com.javiermarsicano.moviex.data.models.MovieResult
import io.reactivex.Single

interface DataRepository {

    fun getPopular(): Single<List<MovieResult>>

    fun getTopRatedMovies(): Single<List<MovieResult>>

    fun getUpcomingMovies(): Single<List<MovieResult>>

    fun getCache(): List<MovieResult>

}