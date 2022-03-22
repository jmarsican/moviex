package com.javiermarsicano.moviex.screen.movies

import com.javiermarsicano.moviex.base.UseCase
import com.javiermarsicano.moviex.data.MovieRepository
import com.javiermarsicano.moviex.data.model.MovieResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class GetTopMoviesUseCase (
    private val repository: MovieRepository,
): UseCase<Int, List<MovieResult>>() {
    override fun execute(parameters: Int): Flow<List<MovieResult>> {
        return repository.getTopRated(parameters)
    }
}