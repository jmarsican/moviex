package com.javiermarsicano.moviex.data.repository

import com.javiermarsicano.moviex.data.models.MovieResult
import com.javiermarsicano.moviex.data.models.VideoData
import io.reactivex.Single

interface DataRepository {

    fun getPopular(): Single<List<MovieResult>>

    fun getTopRatedMovies(): Single<List<MovieResult>>

    fun getUpcomingMovies(): Single<List<MovieResult>>

    fun getVideo(id: String): Single<List<VideoData>>

    fun getCache(): List<MovieResult>

}