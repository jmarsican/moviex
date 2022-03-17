package com.javiermarsicano.moviex.data.network

import com.javiermarsicano.moviex.data.dto.MovieResponse
import com.javiermarsicano.moviex.data.dto.VideosResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_VERSION = 3

interface ServiceApi {

    @GET("/$API_VERSION/movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("/$API_VERSION/movie/{movie_id}/videos")
    suspend fun getVideos(
        @Path("movie_id") id: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): VideosResponse

}