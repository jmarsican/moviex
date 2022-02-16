package com.javiermarsicano.moviex.data

import android.content.Context
import android.net.Uri
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.javiermarsicano.moviex.BuildConfig
import com.javiermarsicano.moviex.data.db.MoviesDatabase
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

private const val CACHE_SIZE = 10 * 1024 * 1024

abstract class BaseRemoteRepository(context: Context, baseUrl: Uri) {
    protected val retrofit: Retrofit
    val db = Room.databaseBuilder(context, MoviesDatabase::class.java, "movies-database")
        .build() //TODO Refactor

    init {
        val loggingInterceptor = HttpLoggingInterceptor { Timber.d(it) }
        loggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .cache(
                Cache(
                    File(context.cacheDir,"okhttp-cache"),
                    CACHE_SIZE.toLong()
                )
            )

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl.toString())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(clientBuilder.build())
            .build()
    }

}