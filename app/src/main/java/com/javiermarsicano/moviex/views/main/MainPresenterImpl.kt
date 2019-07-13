package com.javiermarsicano.moviex.views.main

import com.javiermarsicano.moviex.common.mvp.BaseMVPPresenter
import java.lang.ref.WeakReference

class MainPresenterImpl (viewReference: WeakReference<MainView>) : BaseMVPPresenter<MainView>(), MainPresenter {
}