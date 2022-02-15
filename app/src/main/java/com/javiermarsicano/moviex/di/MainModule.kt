package com.javiermarsicano.moviex.di

import android.content.Context
import com.javiermarsicano.moviex.BuildConfig
import com.javiermarsicano.moviex.data.MovieRepository
import com.javiermarsicano.moviex.data.MovieRepositoryImpl
import com.javiermarsicano.moviex.screen.movies.GetTopMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(@ApplicationContext context: Context): MovieRepository =
        MovieRepositoryImpl(context, BuildConfig.API_BASE_URL)

    @Singleton
    @Provides
    fun provideGetMoviesInteractor(repo: MovieRepository): GetTopMoviesUseCase = GetTopMoviesUseCase(repo)
}