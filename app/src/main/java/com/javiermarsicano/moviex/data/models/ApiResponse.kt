package com.javiermarsicano.moviex.data.models


import com.google.gson.annotations.SerializedName

data class ApiResponse(
        @SerializedName("page")
    val mPage: Int,
        @SerializedName("results")
    val mResults: List<MovieResult>,
        @SerializedName("total_pages")
    val mTotalPages: Int,
        @SerializedName("total_results")
    val mTotalResults: Int
)