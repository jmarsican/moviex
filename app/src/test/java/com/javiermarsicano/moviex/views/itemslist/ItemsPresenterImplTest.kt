package com.javiermarsicano.moviex.views.itemslist

import com.javiermarsicano.moviex.data.models.MovieResult
import com.javiermarsicano.moviex.data.models.VideoData
import com.javiermarsicano.moviex.data.repository.DataRepository
import com.javiermarsicano.moviex.data.repository.DataRepositoryImpl
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Rule
import org.mockito.Matchers
import java.lang.Exception


class ItemsPresenterImplTest {

    @get:Rule
    val immediateSchedulersRule = TrampolineSchedulerRule()

    private lateinit var sut: ItemsPresenterImpl

    private lateinit var repository: DataRepository
    private lateinit var view: ItemsView

    private val MOVIE_ID = 123
    private val movieMock = MovieResult(MOVIE_ID,
            "path",
            "lang",
            "title",
            "overview",
            0.0,
            "path",
            "date",
            "title",
            false,
            0.0,
            0,
            0
    )

    @Before
    fun setUp() {
        repository = mock(DataRepository::class.java)
        view = mock(ItemsView::class.java)
        sut = ItemsPresenterImpl(repository)
        sut.onBindView(view)

    }

    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `Get  popular  movies failure`() {
        //given
        val error = "Error occurred"
        val data = Single.error<List<MovieResult>>(Exception(error))

        `when`(repository.getPopular()).thenReturn(data)

        sut.getPopular()

        //then
        verify(view).onError(Matchers.anyString())
    }

    @Test
    fun `Get  popular  movies successful`() {
        //given
        val moviesList = listOf(movieMock)
        val data = Single.just(moviesList)

        `when`(repository.getPopular()).thenReturn(data)

        sut.getPopular()

        //then
        val argumentCaptor = ArgumentCaptor<List<MovieResult>>()
        verify(view).showSearchResult(argumentCaptor.capture())
        assertEquals(moviesList, argumentCaptor.value)
    }

    @Test
    fun `Get upcoming movies failure`() {
        //given
        val error = "Error occurred"
        val data = Single.error<List<MovieResult>>(Exception(error))

        `when`(repository.getUpcomingMovies()).thenReturn(data)

        sut.getUpcoming()

        //then
        verify(view).onError(Matchers.anyString())
    }

    @Test
    fun `Get  upcoming  movies successful`() {
        //given
        val moviesList = listOf(movieMock)
        val data = Single.just(moviesList)

        `when`(repository.getUpcomingMovies()).thenReturn(data)

        sut.getUpcoming()

        //then
        val argumentCaptor = ArgumentCaptor<List<MovieResult>>()
        verify(view).showSearchResult(argumentCaptor.capture())
        assertEquals(moviesList, argumentCaptor.value)
    }

    @Test
    fun `Get Top Rated movies failure`() {
        //given
        val error = "Error occurred"
        val data = Single.error<List<MovieResult>>(Exception(error))

        `when`(repository.getTopRatedMovies()).thenReturn(data)

        sut.getTopRated()

        //then
        verify(view).onError(Matchers.anyString())
    }

    @Test
    fun `Get  top rated  movies successful`() {
        //given
        val moviesList = listOf(movieMock)
        val data = Single.just(moviesList)

        `when`(repository.getTopRatedMovies()).thenReturn(data)

        sut.getTopRated()

        //then
        val argumentCaptor = ArgumentCaptor<List<MovieResult>>()
        verify(view).showSearchResult(argumentCaptor.capture())
        assertEquals(moviesList, argumentCaptor.value)
    }

    @Test
    fun getVideo() {
        val videosList = listOf(VideoData(MOVIE_ID.toString(),"key","name","site",0,"type"))
        val videoData = Single.just(videosList)

        `when`(repository.getVideo(MOVIE_ID.toString())).thenReturn(videoData)

        sut.getVideo(movieMock)

        //then
        verify(view).openLink(DataRepositoryImpl.Companion.VIDEOS_URL+videosList[0].key)
    }
}