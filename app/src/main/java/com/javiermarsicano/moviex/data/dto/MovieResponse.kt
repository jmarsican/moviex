package com.javiermarsicano.moviex.data.dto

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val page: Int,
    val results: List<MovieResult>,
    @SerializedName("total_pages")
    val mTotalPages: Int,
    @SerializedName("total_results")
    val mTotalResults: Int
)
