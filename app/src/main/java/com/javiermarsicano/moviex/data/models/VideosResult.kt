package com.javiermarsicano.moviex.data.models


import com.google.gson.annotations.SerializedName

data class VideosResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<VideoData>
)