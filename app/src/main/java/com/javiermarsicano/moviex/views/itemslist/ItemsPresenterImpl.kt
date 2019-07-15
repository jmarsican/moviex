package com.javiermarsicano.moviex.views.itemslist

import com.javiermarsicano.moviex.common.mvp.BaseMVPPresenter
import com.javiermarsicano.moviex.data.models.MovieResult
import com.javiermarsicano.moviex.data.repository.DataRepository
import com.javiermarsicano.moviex.data.repository.DataRepositoryImpl.Companion.VIDEOS_URL
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ItemsPresenterImpl @Inject constructor(
        private val dataRepository: DataRepository) : BaseMVPPresenter<ItemsView>(), ItemsPresenter {

    override fun getPopular() {
        searchRepos(dataRepository.getPopular())
    }

    override fun getUpcoming() {
        searchRepos(dataRepository.getUpcomingMovies())
    }

    override fun getTopRated() {
        searchRepos(dataRepository.getTopRatedMovies())
    }

    private fun searchRepos(observable: Single<List<MovieResult>>) {

        viewReference.get()?.showLoading()

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                        viewReference.get()?.hideLoading()
                        viewReference.get()?.showSearchResult(it)
                    }, {
                        viewReference.get()?.hideLoading()
                        viewReference.get()?.onError(it.localizedMessage)
                    }

                )
                .bindToLifecycle()
    }

    fun getVideo(movie: MovieResult) {
        viewReference.get()?.showLoading()

        dataRepository.getVideo(movie.id.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    viewReference.get()?.hideLoading()
                    viewReference.get()?.openLink(VIDEOS_URL + it[0].key)
                }, {
                    viewReference.get()?.hideLoading()
                    viewReference.get()?.onError(it.localizedMessage)
                }

                )
                .bindToLifecycle()
    }

    fun getCache() = dataRepository.getCache()

}