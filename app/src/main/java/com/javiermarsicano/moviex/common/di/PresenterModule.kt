package com.javiermarsicano.moviex.common.di

import android.app.Application
import com.javiermarsicano.moviex.data.repository.DataRepository
import com.javiermarsicano.moviex.data.repository.DataRepositoryImpl
import com.javiermarsicano.moviex.data.services.MoviesService
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class PresenterModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideRestApi() : Retrofit {

        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Timber.i(it) })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okhttpClient = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()

        return Retrofit.Builder()
                .baseUrl(DataRepositoryImpl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okhttpClient)
                .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit) : MoviesService {
        return retrofit.create(MoviesService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(service: MoviesService) : DataRepository {
        return DataRepositoryImpl(service)
    }
}