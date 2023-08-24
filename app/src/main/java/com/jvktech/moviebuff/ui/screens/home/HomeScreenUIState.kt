package com.jvktech.moviebuff.ui.screens.home

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.DetailPresentable
import com.jvktech.moviebuff.data.model.Presentable
import com.jvktech.moviebuff.data.model.movie.MovieFavorite
import com.jvktech.moviebuff.data.model.movie.RecentlyBrowsedMovie
import com.jvktech.moviebuff.data.model.tvshow.RecentlyBrowsedTvShow
import com.jvktech.moviebuff.data.model.tvshow.TvShowFavorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class MovieScreenUIState(
    val moviesState: MoviesState,
    val favoriteMovies: Flow<PagingData<MovieFavorite>>,
    val favoriteTvSeries: Flow<PagingData<TvShowFavorite>>,
    val recentlyBrowsedMovies: Flow<PagingData<RecentlyBrowsedMovie>>,
    val recentlyBrowsedTvSeries: Flow<PagingData<RecentlyBrowsedTvShow>>

) {
    companion object {
        val default: MovieScreenUIState = MovieScreenUIState(
            moviesState = MoviesState.default,
            favoriteMovies = emptyFlow(),
            favoriteTvSeries = emptyFlow(),
            recentlyBrowsedMovies = emptyFlow(),
            recentlyBrowsedTvSeries = emptyFlow()
        )
    }
}

@Stable
data class MoviesState(
    // Movies
    val discoverMovies: Flow<PagingData<Presentable>>,
    val upcomingMovies: Flow<PagingData<Presentable>>,
    val topRatedMovies: Flow<PagingData<Presentable>>,
    val trendingMovies: Flow<PagingData<Presentable>>,
    val nowPlayingMovies: Flow<PagingData<DetailPresentable>>,
    // Tv series
    val onTheAirTvSeries: Flow<PagingData<DetailPresentable>>,
    val discoverTvSeries: Flow<PagingData<Presentable>>,
    val topRatedTvSeries: Flow<PagingData<Presentable>>,
    val trendingTvSeries: Flow<PagingData<Presentable>>,
    val airingTodayTvSeries: Flow<PagingData<Presentable>>
) {
    companion object {
        val default: MoviesState = MoviesState(
            // Movies
            discoverMovies = emptyFlow(),
            upcomingMovies = emptyFlow(),
            topRatedMovies = emptyFlow(),
            trendingMovies = emptyFlow(),
            nowPlayingMovies = emptyFlow(),
            // Tv Series
            onTheAirTvSeries = emptyFlow(),
            discoverTvSeries = emptyFlow(),
            topRatedTvSeries = emptyFlow(),
            trendingTvSeries = emptyFlow(),
            airingTodayTvSeries = emptyFlow()
        )
    }
}

