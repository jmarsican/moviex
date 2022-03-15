package com.javiermarsicano.moviex.data

sealed class Resource<out T> {
    data class Loading<out T>(val partialData: T? = null): Resource<T>()
    data class Success<out T>(val data: T): Resource<T>()
    data class Error(val e: Throwable): Resource<Nothing>()
}



