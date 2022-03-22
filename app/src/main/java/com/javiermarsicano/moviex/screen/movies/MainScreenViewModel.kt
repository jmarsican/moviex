package com.javiermarsicano.moviex.screen.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.javiermarsicano.moviex.base.BaseReactiveViewModel
import com.javiermarsicano.moviex.common.Event
import com.javiermarsicano.moviex.common.StatusViewState
import com.javiermarsicano.moviex.data.Resource
import com.javiermarsicano.moviex.data.model.MovieResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getTopMoviesUseCase: GetTopMoviesUseCase
): BaseReactiveViewModel() {

    private val moviesLiveData = MutableLiveData<List<MovieResult>>()
    val moviesObservable: LiveData<List<MovieResult>> get() = moviesLiveData

    private val status = MutableLiveData<Event<StatusViewState>>()
    val statusObservable: LiveData<Event<StatusViewState>> get() = status

    private var job: Job? = null

    fun getTopMovies(page: Int) {
        job?.cancel()
        job = viewModelScope.launch {
            getTopMoviesUseCase.invoke(page).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        status.value = Event(StatusViewState.Content)
                        result.data.let {  moviesLiveData.value = it }
                    }
                    is Resource.Error -> {
                        status.value = Event(StatusViewState.Error(result.e))
                    }
                    is Resource.Loading -> {
                        status.value = Event(StatusViewState.Loading)
                    }
                }
            }
        }
    }

}