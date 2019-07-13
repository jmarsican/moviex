package com.javiermarsicano.moviex

import android.app.Application
import com.javiermarsicano.moviex.common.di.DIComponent
import com.javiermarsicano.moviex.common.di.DaggerDIComponent
import com.javiermarsicano.moviex.common.di.PresenterModule
import timber.log.Timber

class MyApplication: Application() {

    lateinit var component: DIComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        this.component = DaggerDIComponent.builder().presenterModule(PresenterModule(this)).build()
    }
}