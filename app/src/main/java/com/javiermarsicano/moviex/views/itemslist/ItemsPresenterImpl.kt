package com.javiermarsicano.moviex.views.itemslist

import com.javiermarsicano.moviex.common.mvp.BaseMVPPresenter
import com.javiermarsicano.moviex.data.models.MovieResult
import com.javiermarsicano.moviex.data.repository.DataRepository
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

    fun getCache() = dataRepository.getCache()

}