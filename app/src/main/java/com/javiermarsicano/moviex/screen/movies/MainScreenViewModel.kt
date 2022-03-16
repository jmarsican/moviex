package com.javiermarsicano.moviex.screen.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.javiermarsicano.moviex.base.BaseReactiveViewModel
import com.javiermarsicano.moviex.common.Event
import com.javiermarsicano.moviex.common.StatusViewState
import com.javiermarsicano.moviex.common.doOnSuccess
import com.javiermarsicano.moviex.common.toViewState
import com.javiermarsicano.moviex.data.model.MovieResult
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getTopMoviesUseCase: GetTopMoviesUseCase
): BaseReactiveViewModel() {

    private val moviesLiveData = MutableLiveData<List<MovieResult>>()
    val moviesObservable: LiveData<List<MovieResult>> get() = moviesLiveData

    private val status = MutableLiveData<Event<StatusViewState>>()
    val statusObservable: LiveData<Event<StatusViewState>> get() = status

    private var topMoviesDisposable: Disposable = Disposables.disposed()

    fun getTopMovies(page: Int) {
        topMoviesDisposable.dispose()
        getTopMoviesUseCase.invoke(page)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                moviesLiveData.value = it
            }
            .subscribe({ resource ->
                status.value = resource.toViewState()
            },{
                status.value = Event(StatusViewState.Error(it))
            })
            .also { disposables += it }
    }
}