package com.javiermarsicano.moviex.screen.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javiermarsicano.moviex.common.Event
import com.javiermarsicano.moviex.common.StatusViewState
import com.javiermarsicano.moviex.common.doOnSuccess
import com.javiermarsicano.moviex.common.toSingleEvent
import com.javiermarsicano.moviex.data.Resource
import com.javiermarsicano.moviex.data.model.MovieResult
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getTopMoviesUseCase: GetTopMoviesUseCase
): ViewModel() {

    private val moviesLiveData = MutableLiveData<List<MovieResult>>()
    val moviesObservable: LiveData<List<MovieResult>> get() = moviesLiveData

    private val status = MutableLiveData<Event<StatusViewState>>()
    val statusObservable: LiveData<Event<StatusViewState>> get() = status

    private var disposable: Disposable = Disposables.disposed()

    fun getTopMovies() {
        disposable.dispose()
        getTopMoviesUseCase.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                moviesLiveData.value = it
            }
            .subscribe({ resource ->
                status.value = when (resource) {
                    is Resource.Loading -> Event(StatusViewState.Loading)
                    is Resource.Success -> Event(StatusViewState.Content)
                    is Resource.Error -> Event(StatusViewState.Error(resource.e))
                }
            },{
                status.value = Event(StatusViewState.Error(it))
            })
            .also { disposable = it }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}