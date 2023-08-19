package com.jvktech.moviebuff.ui.screens.browse.tvshows

import android.os.Parcelable
import com.jvktech.moviebuff.data.model.tvshow.TvShowType
import kotlinx.parcelize.Parcelize

@Parcelize
data class BrowseTvShowsScreenArgs(
    val tvShowType: TvShowType
) : Parcelable