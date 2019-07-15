package com.javiermarsicano.moviex.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.javiermarsicano.moviex.data.models.MovieResult

@Database(entities = arrayOf(MovieResult::class), version = 2)
abstract class RepoDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDAO
}