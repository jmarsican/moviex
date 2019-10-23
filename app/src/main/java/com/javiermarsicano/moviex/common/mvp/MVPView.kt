package com.javiermarsicano.moviex.common.mvp

interface MVPView {
    fun showLoading()
    fun hideLoading()
    fun onError(resId: Int)
    fun onError(message: String?)
    fun hideKeyboard()
}