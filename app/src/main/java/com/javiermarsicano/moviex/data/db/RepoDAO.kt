package com.javiermarsicano.moviex.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.javiermarsicano.moviex.data.models.MovieResult
import io.reactivex.Single

@Dao
interface RepoDAO {

    @Query("SELECT * FROM movies WHERE category = :category")
    fun getMovies(category: Int): Single<List<MovieResult>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeMovies(movies: List<MovieResult>)

}
