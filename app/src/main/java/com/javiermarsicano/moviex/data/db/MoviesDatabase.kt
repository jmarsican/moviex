package com.javiermarsicano.moviex.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.javiermarsicano.moviex.data.dto.MovieResultDTO
import com.javiermarsicano.moviex.data.model.MovieResult

@Database(entities = [MovieResultDTO::class], version = 1)
@TypeConverters(Converters::class)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun getMoviesDao(): MovieDao
}