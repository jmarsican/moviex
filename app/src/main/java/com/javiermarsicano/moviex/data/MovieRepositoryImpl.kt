package com.javiermarsicano.moviex.data

import android.content.Context
import android.net.Uri
import com.javiermarsicano.moviex.BuildConfig
import com.javiermarsicano.moviex.data.model.MovieResult
import com.javiermarsicano.moviex.data.network.ServiceApi
import io.reactivex.Single
import java.util.*

class MovieRepositoryImpl(context: Context, url: String):
    MovieRepository,
    BaseRemoteRepository(context, Uri.parse(url)) {

    private val serviceApi = retrofit.create(ServiceApi::class.java)

    override fun getTopRated(): Single<List<MovieResult>> {
        return serviceApi.getTopRated(BuildConfig.API_KEY, Locale.getDefault().toString(), 1)
            .map {
                it.results.map { dto ->
                    dto.toModel()
                }
            }
            .flatMap {  db.getMoviesDao().saveTopMovies(it).toSingleDefault(it) }
            .onErrorResumeNext {  db.getMoviesDao().getTopMovies() }
    }
}