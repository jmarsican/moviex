package com.javiermarsicano.moviex.common.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(PresenterModule::class))
interface DIComponent {


}