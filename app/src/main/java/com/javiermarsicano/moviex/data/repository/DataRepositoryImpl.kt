package com.javiermarsicano.moviex.data.repository

import com.javiermarsicano.moviex.data.models.MovieResult
import com.javiermarsicano.moviex.data.services.MoviesService
import io.reactivex.Single


class DataRepositoryImpl(private val moviesService: MoviesService): DataRepository {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie"
    }

    private val cachedResults: ArrayList<MovieResult> = arrayListOf()

    override fun searchRepositories(query: String): Single<List<MovieResult>> {
        return moviesService.getPopular(query)
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