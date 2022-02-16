package com.javiermarsicano.moviex.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.javiermarsicano.moviex.data.model.MovieResult

@Database(entities = [MovieResult::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun getMoviesDao(): MovieDao
}