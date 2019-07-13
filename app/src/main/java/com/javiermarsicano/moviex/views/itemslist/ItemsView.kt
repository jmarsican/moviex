package com.javiermarsicano.moviex.views.itemslist

import com.javiermarsicano.moviex.common.mvp.MVPView
import com.javiermarsicano.moviex.data.models.MovieResult

interface ItemsView : MVPView {
    fun showSearchResult(result: List<MovieResult>?)
}