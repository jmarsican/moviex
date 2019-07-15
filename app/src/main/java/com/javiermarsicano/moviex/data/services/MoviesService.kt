package com.javiermarsicano.moviex.data.services

import com.javiermarsicano.moviex.data.models.ApiResponse
import com.javiermarsicano.moviex.data.models.VideosResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "13c5955def4848143ee749a3a98b1f0e"
const val API_VERSION = 3
const val DEFAULT_LANGUAGE = "en-US"

interface MoviesService {

    @GET("/$API_VERSION/movie/{movie_id}/videos")
    fun getVideos(@Path("movie_id") id: String, @Query("api_key") query: String = API_KEY, @Query("language")language: String = DEFAULT_LANGUAGE): Single<VideosResult>

    @GET("/$API_VERSION/movie/popular")
    fun getPopular(@Query("api_key") query: String = API_KEY, @Query("language")language: String = DEFAULT_LANGUAGE, @Query("page")page: Int): Single<ApiResponse>

    @GET("/$API_VERSION/movie/top_rated")
    fun getTop(@Query("api_key") query: String = API_KEY, @Query("language")language: String = DEFAULT_LANGUAGE, @Query("page")page: Int): Single<ApiResponse>

    @GET("/$API_VERSION/movie/upcoming")
    fun getUpcoming(@Query("api_key") query: String = API_KEY, @Query("language")language: String = DEFAULT_LANGUAGE, @Query("page")page: Int): Single<ApiResponse>
}