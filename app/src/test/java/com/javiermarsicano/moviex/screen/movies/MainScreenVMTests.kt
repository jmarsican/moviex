package com.javiermarsicano.moviex.screen.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.javiermarsicano.moviex.ImmediateSchedulerRule
import com.javiermarsicano.moviex.common.Event
import com.javiermarsicano.moviex.common.StatusViewState
import com.javiermarsicano.moviex.data.Resource
import com.javiermarsicano.moviex.data.model.MovieResult
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@RunWith(MockitoJUnitRunner::class)
class MainScreenVMTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Rule
    @JvmField
    val rule = ImmediateSchedulerRule()

    private lateinit var sut: MainScreenViewModel

    @Mock
    private lateinit var getTopMoviesUseCaseMock: GetTopMoviesUseCase
    @Captor
    private lateinit var moviesArgumentCaptor: ArgumentCaptor<List<MovieResult>>
    @Mock
    private lateinit var moviesLiveDataObserver: Observer<List<MovieResult>>
    @Captor
    private lateinit var statusArgumentCaptor: ArgumentCaptor<Event<StatusViewState>>
    @Mock
    private lateinit var statusLiveDataObserver: Observer<Event<StatusViewState>>

    val movieResult = MovieResult(
        0,
        null,
        null,
        null,
        null,
        null,
        "",
        null,
        "",
        false,
        null,
        null
    )

    @Before
    fun setUp() {
        sut = MainScreenViewModel(getTopMoviesUseCaseMock)
    }

    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `test get top movies successful`() {
        sut.moviesObservable.observeForever(moviesLiveDataObserver)
        sut.statusObservable.observeForever(statusLiveDataObserver)
        val successResourceWithData = Resource.Success(listOf(movieResult))
        whenever(getTopMoviesUseCaseMock.invoke(Unit))
            .thenReturn(Observable.just(successResourceWithData))

        sut.getTopMovies()

        verify(moviesLiveDataObserver).onChanged(moviesArgumentCaptor.capture())
        verify(statusLiveDataObserver).onChanged(statusArgumentCaptor.capture())
        assertThat(statusArgumentCaptor.value.peekContent(), instanceOf(StatusViewState.Content::class.java))
        assertFalse(statusArgumentCaptor.value.hasBeenHandled.get())
        assertEquals(movieResult, moviesArgumentCaptor.value.first())
    }

    @Test
    fun `get top movies error`() {
        sut.statusObservable.observeForever(statusLiveDataObserver)
        val error = Throwable("ERROR!")
        whenever(getTopMoviesUseCaseMock.invoke(Unit))
            .thenReturn(Observable.error(error))

        sut.getTopMovies()

        verify(statusLiveDataObserver).onChanged(statusArgumentCaptor.capture())
        assertThat(statusArgumentCaptor.value.peekContent(), instanceOf(StatusViewState.Error::class.java))
        assertEquals(error, (statusArgumentCaptor.value.peekContent() as StatusViewState.Error).exception)
        assertFalse(statusArgumentCaptor.value.hasBeenHandled.get())
    }
}