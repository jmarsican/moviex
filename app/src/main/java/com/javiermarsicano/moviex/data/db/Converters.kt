package com.javiermarsicano.moviex.data.db

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class Converters {
    private val dbDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    @TypeConverter
    fun fromString(s: String): Date? {
        return dbDateFormat.parse(s)
    }

    @TypeConverter
    fun dateToString(date: Date): String {
        return dbDateFormat.format(date)
    }
}