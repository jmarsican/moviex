package com.javiermarsicano.moviex.data.services

import com.javiermarsicano.moviex.data.models.ApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("/3/movie/popular")
    fun getPopular(@Query("api_key") query: String, @Query("language")language: String, @Query("page")page: Int): Single<ApiResponse>
}