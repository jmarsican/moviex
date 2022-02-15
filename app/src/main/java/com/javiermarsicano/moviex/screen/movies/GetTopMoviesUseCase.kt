package com.javiermarsicano.moviex.screen.movies

import android.content.Context
import com.javiermarsicano.moviex.BuildConfig
import com.javiermarsicano.moviex.base.UseCase
import com.javiermarsicano.moviex.data.MovieRepository
import com.javiermarsicano.moviex.data.MovieRepositoryImpl
import com.javiermarsicano.moviex.data.model.MovieResult
import io.reactivex.Single

class GetTopMoviesUseCase(
    context: Context,
    private val repository: MovieRepository = MovieRepositoryImpl(context, BuildConfig.API_BASE_URL) //TODO use Dep Inj
): UseCase<List<MovieResult>> {
    override fun execute(): Single<List<MovieResult>> = repository.getTopRated()
}