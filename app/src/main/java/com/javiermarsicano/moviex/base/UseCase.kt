package com.javiermarsicano.moviex.base

import io.reactivex.Observable

interface UseCase<R> {
    fun execute(): Observable<R>
}