package com.javiermarsicano.moviex.data.services

import com.javiermarsicano.moviex.data.models.ApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("/movie/popular")
    fun getPopular(@Query("q") query: String): Single<ApiResponse>
}