package com.javiermarsicano.moviex.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.javiermarsicano.moviex.BuildConfig
import com.javiermarsicano.moviex.data.MovieRepository
import com.javiermarsicano.moviex.data.MovieRepositoryImpl
import com.javiermarsicano.moviex.data.db.MoviesDatabase
import com.javiermarsicano.moviex.data.network.ServiceApi
import com.javiermarsicano.moviex.screen.movies.GetTopMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val CACHE_SIZE = 10 * 1024 * 1024
internal const val API_DATE_PATTERN = "yyyy-MM-dd"

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Singleton
    @Provides
    fun provideHttpClient(@ApplicationContext context: Context): OkHttpClient {
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
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        val gsonBuilder = GsonBuilder()
            .setDateFormat(API_DATE_PATTERN)

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideMoviesApi(retrofit: Retrofit): ServiceApi = retrofit.create(ServiceApi::class.java)

    @Singleton
    @Provides
    fun provideMoviesDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(context, MoviesDatabase::class.java, "movies-database")
            .build()
    }

    @Singleton
    @Provides
    fun provideMoviesRepository(api: ServiceApi, db: MoviesDatabase): MovieRepository =
        MovieRepositoryImpl(api, db.getMoviesDao())

    @Singleton
    @Provides
    fun provideGetMoviesInteractor(repo: MovieRepository): GetTopMoviesUseCase = GetTopMoviesUseCase(repo)
}