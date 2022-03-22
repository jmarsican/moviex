package com.javiermarsicano.moviex.data

import com.javiermarsicano.moviex.BuildConfig
import com.javiermarsicano.moviex.data.db.MovieDao
import com.javiermarsicano.moviex.data.model.MovieResult
import com.javiermarsicano.moviex.data.network.ServiceApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.util.*

class MovieRepositoryImpl(
    private val serviceApi: ServiceApi,
    private val moviesDao: MovieDao,
): MovieRepository {

    override fun getTopRated(page: Int): Flow<List<MovieResult>> {
        return flow {
            val result = serviceApi.getTopRated(BuildConfig.API_KEY, Locale.getDefault().toString(), page)
            moviesDao.saveTopMovies(result.results)
            emit(result.results)
        }.catch {
            val result = moviesDao.getTopMovies()
            if (result.isEmpty()) throw DataNotAvailableException("Could not retrieve data from local repository")
            emit(result)
        }.map {
            it.map { dto-> dto.toModel() }
        }
    }
}

class DataNotAvailableException(msg: String): Throwable(msg)