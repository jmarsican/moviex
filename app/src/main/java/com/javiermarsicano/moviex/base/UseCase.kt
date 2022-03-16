package com.javiermarsicano.moviex.base

import com.javiermarsicano.moviex.data.Resource
import io.reactivex.Observable
import io.reactivex.Scheduler

abstract class UseCase<in P, R>(private val scheduler: Scheduler) {

    /** Executes the use case asynchronously and returns a [Resource].
     *
     * @return a [Resource].
     *
     * @param parameters the input parameters to run the use case with
     */
    fun invoke(parameters: P): Observable<Resource<R>> {
        return execute(parameters)
            .map<Resource<R>> { //better to use asyncResource() extension
                Resource.Success(it)
            }
            .startWith(Resource.Loading())
            .onErrorReturn { Resource.Error(it) }
            .subscribeOn(scheduler)
    }

    /**
     * Override this to set the code to be executed.
     */
    protected abstract fun execute(parameters: P): Observable<R>
}