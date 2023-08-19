package com.jvktech.moviebuff.ui.screens.related.movies

import android.os.Parcelable
import com.jvktech.moviebuff.data.model.RelationType
import kotlinx.parcelize.Parcelize

@Parcelize
data class RelatedMoviesScreenArgs(
    val movieId: Int,
    val type: RelationType,
    val startRoute: String
) : Parcelable