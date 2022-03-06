package com.javiermarsicano.moviex.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.javiermarsicano.moviex.data.dto.MovieResultDTO
import com.javiermarsicano.moviex.data.model.MovieResult
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_result")
    fun getTopMovies(): Single<List<MovieResultDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTopMovies(movie: List<MovieResultDTO>): Completable
}