package com.javiermarsicano.moviex.base

import com.javiermarsicano.moviex.data.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext

abstract class UseCase<in P, R>() {

    /** Executes the use case asynchronously and returns a [Resource].
     *
     * @return a [Resource].
     *
     * @param parameters the input parameters to run the use case with
     */
    fun invoke(parameters: P): Flow<Resource<R>> = execute(parameters)
        .onStart { emit(Resource.Loading()) }


    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract fun execute(parameters: P): Flow<Resource<R>>
}