package com.jvktech.moviebuff.data.model

import androidx.compose.runtime.Stable

@Stable
interface Presentable {
    val id: Int
    val title: String
    val posterPath: String?
    val voteAverage: Float
    val voteCount: Int
}

@Stable
interface DetailPresentable : Presentable {
    val adult: Boolean?
    val overview: String?
    val backdropPath: String?
}