package com.javiermarsicano.moviex.screen.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
    val moviesObservable get() = moviesLiveData

    private var disposable: Disposable = Disposables.disposed()

    fun getTopMovies() {
        disposable.dispose()
        getTopMoviesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                moviesLiveData.value = it
            },{

            })
            .also { disposable = it }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}