package com.javiermarsicano.moviex.screen.movies

import com.javiermarsicano.moviex.base.UseCase
import com.javiermarsicano.moviex.data.MovieRepository
import com.javiermarsicano.moviex.data.model.MovieResult
import io.reactivex.Scheduler
import io.reactivex.Single

class GetTopMoviesUseCase (
    private val repository: MovieRepository,
    private val scheduler: Scheduler
): UseCase<List<MovieResult>> {
    override fun execute(): Single<List<MovieResult>> {
        return repository.getTopRated()
            .subscribeOn(scheduler)
    }
}