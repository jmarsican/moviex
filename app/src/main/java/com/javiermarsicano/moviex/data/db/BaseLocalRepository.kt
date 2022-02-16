package com.javiermarsicano.moviex.data.db

import android.content.Context
import androidx.room.Room

class BaseLocalRepository(val context: Context) {
    val db = Room.databaseBuilder(context, MoviesDatabase::class.java, "movies-database")
        .build()
}