package com.javiermarsicano.moviex.screen.movies

import com.javiermarsicano.moviex.base.UseCase
import com.javiermarsicano.moviex.data.MovieRepository
import com.javiermarsicano.moviex.data.model.MovieResult
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class GetTopMoviesUseCase (
    private val repository: MovieRepository,
    scheduler: Scheduler = Schedulers.io()
): UseCase<Int, List<MovieResult>>(scheduler) {
    override fun execute(parameters: Int): Observable<List<MovieResult>> {
        return repository.getTopRated(parameters).toObservable()
    }
}