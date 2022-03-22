package com.javiermarsicano.moviex.screen.movies

import com.javiermarsicano.moviex.base.UseCase
import com.javiermarsicano.moviex.data.MovieRepository
import com.javiermarsicano.moviex.data.Resource
import com.javiermarsicano.moviex.data.model.MovieResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GetTopMoviesUseCase (
    private val repository: MovieRepository,
): UseCase<Int, List<MovieResult>>() {
    override fun execute(parameters: Int): Flow<Resource<List<MovieResult>>> {
        return repository.getTopRated(parameters)
            .map { Resource.Success(it) }
            .catch { Resource.Error(it) }
    }
}