package com.javiermarsicano.moviex.data

sealed class Resource<out T> {
    data class Loading<out T>(val partialData: T? = null): Resource<T>()
    data class Success<out T>(val data: T): Resource<T>()
    data class Error(val e: Throwable): Resource<Nothing>()
}

/**
 * Simplify mapping only for success status
 * */
inline fun <T, R> Resource<T>.map(transform: (T) -> R): Resource<R> {
    return when (this) {
        is Resource.Success -> Resource.Success(transform(data))
        is Resource.Error -> Resource.Error(e)
        is Resource.Loading -> Resource.Loading()
    }
}

