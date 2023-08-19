package com.jvktech.moviebuff.ui.screens.seasons

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeasonDetailsScreenArgs(
    val tvShowId: Int,
    val seasonNumber: Int,
    val startRoute: String
) : Parcelable