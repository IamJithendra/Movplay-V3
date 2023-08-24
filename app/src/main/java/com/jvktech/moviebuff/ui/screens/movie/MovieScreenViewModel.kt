package com.jvktech.moviebuff.ui.screens.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.domain.usecase.GetDeviceLanguageUseCaseImpl
import com.jvktech.moviebuff.domain.usecase.movie.*
import com.jvktech.moviebuff.domain.usecase.tvshow.GetAiringTodayTvShowsUseCaseImpl
import com.jvktech.moviebuff.domain.usecase.tvshow.GetDiscoverAllTvShowsUseCaseImpl
import com.jvktech.moviebuff.domain.usecase.tvshow.GetFavoritesTvShowsUseCaseImpl
import com.jvktech.moviebuff.domain.usecase.tvshow.GetOnTheAirTvShowsUseCaseImpl
import com.jvktech.moviebuff.domain.usecase.tvshow.GetRecentlyBrowsedTvShowsUseCaseImpl
import com.jvktech.moviebuff.domain.usecase.tvshow.GetTopRatedTvShowsUseCaseImpl
import com.jvktech.moviebuff.domain.usecase.tvshow.GetTrendingTvShowsUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MovieScreenViewModel @Inject constructor(
    // Movies
    private val getDeviceLanguageUseCase: GetDeviceLanguageUseCaseImpl,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCaseImpl,
    private val getDiscoverAllMoviesUseCase: GetDiscoverAllMoviesUseCaseImpl,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCaseImpl,
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCaseImpl,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCaseImpl,
    private val favoritesMoviesUseCase: GetFavoritesMoviesUseCaseImpl,
    private val getRecentlyBrowsedMoviesUseCase: GetRecentlyBrowsedMoviesUseCaseImpl,
    // Tv series
    private val getOnTheAirTvShowsUseCase: GetOnTheAirTvShowsUseCaseImpl,
    private val getDiscoverAllTvShowsUseCase: GetDiscoverAllTvShowsUseCaseImpl,
    private val getTopRatedTvShowsUseCase: GetTopRatedTvShowsUseCaseImpl,
    private val getTrendingTvShowsUseCase: GetTrendingTvShowsUseCaseImpl,
    private val getAiringTodayTvShowsUseCase: GetAiringTodayTvShowsUseCaseImpl,
    private val getFavoritesTvShowsUseCase: GetFavoritesTvShowsUseCaseImpl,
    private val getRecentlyBrowsedTvShowsUseCase: GetRecentlyBrowsedTvShowsUseCaseImpl
) : ViewModel() {
    private val deviceLanguage: Flow<DeviceLanguage> = getDeviceLanguageUseCase()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val moviesState: StateFlow<MoviesState> = deviceLanguage.mapLatest { deviceLanguage ->
        MoviesState(
            // Movies
            nowPlayingMovies = getNowPlayingMoviesUseCase(deviceLanguage, true).cachedIn(viewModelScope),
            discoverMovies = getDiscoverAllMoviesUseCase(deviceLanguage).cachedIn(viewModelScope),
            upcomingMovies = getUpcomingMoviesUseCase(deviceLanguage).cachedIn(viewModelScope),
            trendingMovies = getTrendingMoviesUseCase(deviceLanguage).cachedIn(viewModelScope),
            topRatedMovies = getTopRatedMoviesUseCase(deviceLanguage).cachedIn(viewModelScope),
            // Tv series
            onTheAirTvSeries = getOnTheAirTvShowsUseCase(deviceLanguage, true)
                .cachedIn(viewModelScope),
            discoverTvSeries = getDiscoverAllTvShowsUseCase(deviceLanguage)
                .cachedIn(viewModelScope),
            topRatedTvSeries = getTopRatedTvShowsUseCase(deviceLanguage)
                .cachedIn(viewModelScope),
            trendingTvSeries = getTrendingTvShowsUseCase(deviceLanguage)
                .cachedIn(viewModelScope),
            airingTodayTvSeries = getAiringTodayTvShowsUseCase(deviceLanguage)
                .cachedIn(viewModelScope)
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(10), MoviesState.default)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<MovieScreenUIState> = moviesState.mapLatest { moviesState ->
        MovieScreenUIState(
            moviesState = moviesState,
            favoriteMovies = favoritesMoviesUseCase().cachedIn(viewModelScope),
            recentlyBrowsedMovies = getRecentlyBrowsedMoviesUseCase().cachedIn(viewModelScope),
            favoriteTvSeries = getFavoritesTvShowsUseCase().cachedIn(viewModelScope),
            recentlyBrowsedTvSeries = getRecentlyBrowsedTvShowsUseCase()
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, MovieScreenUIState.default)
}