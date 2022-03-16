package com.javiermarsicano.moviex.screen.movies

import com.javiermarsicano.moviex.base.UseCase
import com.javiermarsicano.moviex.data.MovieRepository
import com.javiermarsicano.moviex.data.Resource
import com.javiermarsicano.moviex.data.model.MovieResult
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetTopMoviesUseCase (
    private val repository: MovieRepository,
    private val scheduler: Scheduler
): UseCase<Resource<List<MovieResult>>> {
    override fun execute(): Observable<Resource<List<MovieResult>>> {
        return repository.getTopRated().toObservable()
            .map<Resource<List<MovieResult>>> { //better to use asyncResource() extension
                Resource.Success(it)
            }
            .startWith(Resource.Loading())
            .onErrorReturn { Resource.Error(it) }
            .subscribeOn(scheduler)
    }
}