package com.javiermarsicano.moviex.base

import com.javiermarsicano.moviex.data.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in P, R>(private val dispatcher: CoroutineDispatcher) {

    /** Executes the use case asynchronously and returns a [Resource].
     *
     * @return a [Resource].
     *
     * @param parameters the input parameters to run the use case with
     */
    suspend fun invoke(parameters: P): Resource<R> {
        return try {
            withContext(dispatcher) {
                execute(parameters).let { Resource.Success(it) }
            }
        } catch (e: Throwable) {
            Resource.Error(e)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}