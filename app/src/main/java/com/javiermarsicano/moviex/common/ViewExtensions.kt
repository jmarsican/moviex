package com.javiermarsicano.moviex.common

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImageFromUrl(
    src: String?,
    imgRes: Int = 0,
    @DrawableRes placeholder: Int? = 0,
    @DrawableRes thumbnail: Int? = 0,
    @DrawableRes error: Int? = 0,
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