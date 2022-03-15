package com.javiermarsicano.moviex.data

import android.content.Context
import android.net.Uri
import com.javiermarsicano.moviex.BuildConfig
import com.javiermarsicano.moviex.data.db.MovieDao
import com.javiermarsicano.moviex.data.model.MovieResult
import com.javiermarsicano.moviex.data.network.ServiceApi
import io.reactivex.Single
import java.util.*

class MovieRepositoryImpl(
    private val serviceApi: ServiceApi,
    private val moviesDao: MovieDao,
): MovieRepository {

    override fun getTopRated(): Single<List<MovieResult>> {
        return serviceApi.getTopRated(BuildConfig.API_KEY, Locale.getDefault().toString(), 1)
            .flatMap {  moviesDao.saveTopMovies(it.results).toSingleDefault(it) }
            .map {
                it.results.map { dto ->
                    dto.toModel()
                }
            }
            .onErrorResumeNext { moviesDao.getTopMovies().map { it.map { dto -> dto.toModel() } } }
    }
}