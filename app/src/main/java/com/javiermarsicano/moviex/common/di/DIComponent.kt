package com.javiermarsicano.moviex.common.di

import com.javiermarsicano.moviex.views.itemslist.ItemsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(PresenterModule::class))
interface DIComponent {

    fun inject(presenter: ItemsFragment)

}