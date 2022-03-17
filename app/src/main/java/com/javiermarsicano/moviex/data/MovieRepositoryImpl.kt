package com.javiermarsicano.moviex.data

import com.javiermarsicano.moviex.BuildConfig
import com.javiermarsicano.moviex.data.db.MovieDao
import com.javiermarsicano.moviex.data.model.MovieResult
import com.javiermarsicano.moviex.data.network.ServiceApi
import java.io.IOException
import java.util.*

class MovieRepositoryImpl(
    private val serviceApi: ServiceApi,
    private val moviesDao: MovieDao,
): MovieRepository {

    override suspend fun getTopRated(page: Int): List<MovieResult> {
        return try {
            val result = serviceApi.getTopRated(BuildConfig.API_KEY, Locale.getDefault().toString(), page)
            moviesDao.saveTopMovies(result.results)
            result.results.map { dto ->
                dto.toModel()
            }
        } catch (e: IOException) {
            val result = moviesDao.getTopMovies().map { dto -> dto.toModel() }
            if (result.isEmpty()) throw DataNotAvailableException("Could not retrieve data from local repository")
            result
        }
    }
}

class DataNotAvailableException(msg: String): Throwable(msg)