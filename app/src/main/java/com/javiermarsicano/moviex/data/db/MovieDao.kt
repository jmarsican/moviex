package com.javiermarsicano.moviex.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.javiermarsicano.moviex.data.dto.MovieResultDTO

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_result")
    suspend fun getTopMovies(): List<MovieResultDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTopMovies(movie: List<MovieResultDTO>)
}