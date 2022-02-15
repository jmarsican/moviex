package com.javiermarsicano.moviex.screen.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.javiermarsicano.moviex.ImmediateSchedulerRule
import com.javiermarsicano.moviex.data.model.MovieResult
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import junit.framework.Assert.assertEquals
import org.junit.After
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
    private lateinit var argumentCaptor: ArgumentCaptor<List<MovieResult>>

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
        val liveDataObserver = mock(Observer::class.java)
        sut.moviesObservable.observeForever(liveDataObserver as Observer<in List<MovieResult>>)
        val movieResult = MovieResult(
            0,
            null,
            null,
            null,
            null,
            null,
            "",
            "",
            "",
            false,
            null,
            null
        )
        whenever(getTopMoviesUseCaseMock.execute())
            .thenReturn(Single.just(listOf(movieResult)))

        sut.getTopMovies()

        verify(getTopMoviesUseCaseMock, times(1)).execute()
        verify(liveDataObserver).onChanged(argumentCaptor.capture())
        assertEquals(movieResult, argumentCaptor.allValues[0].first())
    }
}