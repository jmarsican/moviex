package com.javiermarsicano.moviex.views.itemslist

import com.javiermarsicano.moviex.common.mvp.MVPPresenter

interface ItemsPresenter : MVPPresenter<ItemsView> {
    fun getPopular()
    fun getUpcoming()
    fun getTopRated()
}