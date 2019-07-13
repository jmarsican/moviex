package com.javiermarsicano.moviex.views.itemslist

import com.javiermarsicano.moviex.common.mvp.MVPPresenter

interface ItemsPresenter : MVPPresenter<ItemsView> {
    fun searchRepos(query: String)
}