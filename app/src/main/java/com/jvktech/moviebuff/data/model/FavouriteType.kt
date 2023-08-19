package com.jvktech.moviebuff.data.model

import androidx.annotation.StringRes
import com.jvktech.moviebuff.R

enum class FavouriteType {
    Movie, TvShow;

    @StringRes
    fun getLabelResourceId() = when (this) {
        Movie -> R.string.favourite_type_movie_label
        TvShow -> R.string.favourite_type_tv_series_label
    }
}