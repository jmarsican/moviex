package com.javiermarsicano.moviex.common

import com.javiermarsicano.moviex.data.Resource

sealed class StatusViewState {

    object Content : StatusViewState()

    data class Error(val exception: Throwable) : StatusViewState()

    object Loading : StatusViewState()
}

inline fun <R> Resource<R>.toViewState(): Event<StatusViewState> {
    return when(this) {
        is Resource.Success -> Event(StatusViewState.Content)
        is Resource.Error -> Event(StatusViewState.Error(e))
        is Resource.Loading -> Event(StatusViewState.Loading)
    }
}