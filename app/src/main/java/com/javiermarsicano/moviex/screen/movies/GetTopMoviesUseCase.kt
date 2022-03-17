package com.javiermarsicano.moviex.screen.movies

import com.javiermarsicano.moviex.base.UseCase
import com.javiermarsicano.moviex.data.MovieRepository
import com.javiermarsicano.moviex.data.model.MovieResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class GetTopMoviesUseCase (
    private val repository: MovieRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.Default
): UseCase<Int, List<MovieResult>>(dispatcher) {
    override suspend fun execute(parameters: Int): List<MovieResult> {
        return repository.getTopRated(parameters)
    }
}