package com.javiermarsicano.moviex.common.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w92"

fun ImageView.setImageUrl(
        src: String?,
        imgRes: Int = 0,
        placeholder: Int? = 0,
        thumbnail: Int? = 0,
        error: Int? = 0,
        crossFade: Boolean = false
) {

    if (src == null && imgRes == 0)
        return

    val requestManager = Glide.with(this.context)
    val glideBuilder = requestManager.load(src ?: imgRes)

    val requestOptions = RequestOptions().apply {
        placeholder?.let { placeholder(placeholder) }
        error?.let { error(error) }
    }
    // Add filters to build transformation

    if (thumbnail != 0) {
        glideBuilder.thumbnail(requestManager.load(thumbnail).apply(requestOptions))
    }

    if (crossFade) {
        glideBuilder.transition(DrawableTransitionOptions.withCrossFade())
    }

    glideBuilder.apply(requestOptions)
    glideBuilder.into(this)
}