package com.javiermarsicano.moviex.common

sealed class StatusViewState {

    object Content : StatusViewState()

    data class Error(val exception: Throwable) : StatusViewState()

    object Loading : StatusViewState()
}