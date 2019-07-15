package com.javiermarsicano.moviex.data.models


import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
        tableName = "movies",
        indices = arrayOf(Index("id"))
)
data class MovieResult(
        @PrimaryKey
        @SerializedName("id")
        val id: Int,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("video")
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int,

        var category: Int
)

enum class Category{
        POPULAR, TOP_RATED, UPCOMING
}