package com.jvktech.moviebuff.ui.screens.browse.movies

import android.os.Parcelable
import com.jvktech.moviebuff.data.model.movie.MovieType
import kotlinx.parcelize.Parcelize

@Parcelize
data class BrowseMoviesScreenArgs(
    val movieType: MovieType
) : Parcelable