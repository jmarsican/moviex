package com.javiermarsicano.moviex.base

import io.reactivex.Single

interface UseCase<R> {
    fun execute(): Single<R>
}