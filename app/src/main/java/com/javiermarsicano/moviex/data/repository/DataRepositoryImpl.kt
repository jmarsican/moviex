package com.javiermarsicano.moviex.data.repository

import com.javiermarsicano.moviex.data.db.RepoDatabase
import com.javiermarsicano.moviex.data.models.Category
import com.javiermarsicano.moviex.data.models.MovieResult
import com.javiermarsicano.moviex.data.models.VideoData
import com.javiermarsicano.moviex.data.services.MoviesService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class DataRepositoryImpl(private val moviesService: MoviesService, private val database: RepoDatabase): DataRepository {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
        const val VIDEOS_URL = "https://www.youtube.com/watch?v="
    }

    private val cachedResults: ArrayList<MovieResult> = arrayListOf()

    override fun getVideo(id: String): Single<List<VideoData>> {
        return moviesService.getVideos(id).map { it.results }
    }

    override fun getPopular(): Single<List<MovieResult>> {
        return moviesService.getPopular(page = 1)
                .doOnSuccess {
                    val repo = it.mResults

                    repo.forEach{ movie -> movie.category = Category.POPULAR.ordinal}

                    saveToLocal(repo)
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io())
                            .subscribe{
                                Timber.d("Stored results of popular")
                            }
                }
                .map { it.mResults }
                .onErrorResumeNext{ database.repoDao().getMovies(Category.POPULAR.ordinal) }

    }

    override fun getTopRatedMovies(): Single<List<MovieResult>> {
        return moviesService.getTop(page = 1)
                .doOnSuccess {
                    val repo = it.mResults

                    repo.forEach{ movie -> movie.category = Category.TOP_RATED.ordinal}

                    saveToLocal(repo)
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io())
                            .subscribe{
                                Timber.d("Stored results of top rated")
                            }
                }
                .map { it.mResults }
                .onErrorResumeNext{ database.repoDao().getMovies(Category.TOP_RATED.ordinal) }
    }

    override fun getUpcomingMovies(): Single<List<MovieResult>> {
        return moviesService.getUpcoming(page = 1)
                .doOnSuccess {
                    val repo = it.mResults

                    repo.forEach{ movie -> movie.category = Category.UPCOMING.ordinal}

                    saveToLocal(repo)
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io())
                            .subscribe{
                                Timber.d("Stored results of upcoming")
                            }
                }
                .map { it.mResults }
                .onErrorResumeNext{ database.repoDao().getMovies(Category.UPCOMING.ordinal) }
    }

    private fun saveToLocal(result: List<MovieResult>)  = Observable.fromCallable {
        cachedResults.clear()
        cachedResults.addAll(result)

        database.repoDao()
                .storeMovies(result)
    }

    override fun getCache(): List<MovieResult> = cachedResults
}