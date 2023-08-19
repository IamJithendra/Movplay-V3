package com.jvktech.moviebuff.ui.screens.related.tvseries

import android.os.Parcelable
import com.jvktech.moviebuff.data.model.RelationType
import kotlinx.parcelize.Parcelize

@Parcelize
data class RelatedTvShowScreenArgs(
    val tvShowId: Int,
    val type: RelationType,
    val startRoute: String
) : Parcelable