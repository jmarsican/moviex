package com.javiermarsicano.moviex.data.network

import com.javiermarsicano.moviex.data.dto.MovieResponse
import com.javiermarsicano.moviex.data.dto.VideosResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_VERSION = 3

interface ServiceApi {

    @GET("/$API_VERSION/movie/top_rated")
    fun getTopRated(apiKey: String, language: String, page: Int): Single<MovieResponse>

    @GET("/$API_VERSION/movie/{movie_id}/videos")
    fun getVideos(
        @Path("movie_id") id: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<VideosResponse>

}