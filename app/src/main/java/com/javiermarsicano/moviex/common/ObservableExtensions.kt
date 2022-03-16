package com.javiermarsicano.moviex.common

import com.javiermarsicano.moviex.data.Resource
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.asyncResource(): Observable<Resource<T>> =
    map<Resource<T>> { Resource.Success(it) }
        .startWith(Resource.Loading())
        .onErrorReturn { throwable ->
            Resource.Error(throwable)
        }
        .subscribeOn(Schedulers.io())

fun <T> Observable<Resource<T>>.doOnSuccess(
    onSuccess: (T) -> (Unit)
): Observable<Resource<T>> {
    return this.doOnNext {
        if (it is Resource.Success) {
            onSuccess(it.data)
        }
    }

}

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}