package com.javiermarsicano.moviex.data.repository

import com.javiermarsicano.moviex.data.models.MovieResult
import com.javiermarsicano.moviex.data.services.MoviesService
import io.reactivex.Single


class DataRepositoryImpl(private val moviesService: MoviesService): DataRepository {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
    }

    private val cachedResults: ArrayList<MovieResult> = arrayListOf()

    override fun getPopular(query: String): Single<List<MovieResult>> {
        return moviesService.getPopular("13c5955def4848143ee749a3a98b1f0e","en-US",1)
                .map { it.mResults }

    }

    override fun getTopRatedMovies(query: String): Single<List<MovieResult>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUpcomingMovies(query: String): Single<List<MovieResult>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCache(): List<MovieResult> = cachedResults
}