package com.javiermarsicano.moviex.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseReactiveViewModel: ViewModel() {
    protected val disposables = CompositeDisposable()

    override fun onCleared() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
        super.onCleared()
    }
}