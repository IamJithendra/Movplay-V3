package com.jvktech.moviebuff.ui.screens.movie

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.DetailPresentable
import com.jvktech.moviebuff.data.model.Presentable
import com.jvktech.moviebuff.data.model.movie.MovieFavourite
import com.jvktech.moviebuff.data.model.movie.RecentlyBrowsedMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class MovieScreenUIState(
    val moviesState: MoviesState,
    val favourites: Flow<PagingData<MovieFavourite>>,
    val recentlyBrowsed: Flow<PagingData<RecentlyBrowsedMovie>>
) {
    companion object {
        val default: MovieScreenUIState = MovieScreenUIState(
            moviesState = MoviesState.default,
            favourites = emptyFlow(),
            recentlyBrowsed = emptyFlow()
        )
    }
}

@Stable
data class MoviesState(
    val discover: Flow<PagingData<Presentable>>,
    val upcoming: Flow<PagingData<Presentable>>,
    val topRated: Flow<PagingData<Presentable>>,
    val trending: Flow<PagingData<Presentable>>,
    val nowPlaying: Flow<PagingData<DetailPresentable>>
) {
    companion object {
        val default: MoviesState = MoviesState(
            discover = emptyFlow(),
            upcoming = emptyFlow(),
            topRated = emptyFlow(),
            trending = emptyFlow(),
            nowPlaying = emptyFlow()
        )
    }
}

