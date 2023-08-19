package com.jvktech.moviebuff.ui.screens.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.domain.usecase.GetDeviceLanguageUseCaseImpl
import com.jvktech.moviebuff.domain.usecase.movie.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MovieScreenViewModel @Inject constructor(
    private val getDeviceLanguageUseCase: GetDeviceLanguageUseCaseImpl,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCaseImpl,
    private val getDiscoverAllMoviesUseCase: GetDiscoverAllMoviesUseCaseImpl,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCaseImpl,
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCaseImpl,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCaseImpl,
    private val favouritesMoviesUseCase: GetFavouriteMoviesUseCaseImpl,
    private val getRecentlyBrowsedMoviesUseCase: GetRecentlyBrowsedMoviesUseCaseImpl
) : ViewModel() {
    private val deviceLanguage: Flow<DeviceLanguage> = getDeviceLanguageUseCase()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val moviesState: StateFlow<MoviesState> = deviceLanguage.mapLatest { deviceLanguage ->
        MoviesState(
            nowPlaying = getNowPlayingMoviesUseCase(deviceLanguage, true).cachedIn(viewModelScope),
            discover = getDiscoverAllMoviesUseCase(deviceLanguage).cachedIn(viewModelScope),
            upcoming = getUpcomingMoviesUseCase(deviceLanguage).cachedIn(viewModelScope),
            trending = getTrendingMoviesUseCase(deviceLanguage).cachedIn(viewModelScope),
            topRated = getTopRatedMoviesUseCase(deviceLanguage).cachedIn(viewModelScope)
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(10), MoviesState.default)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<MovieScreenUIState> = moviesState.mapLatest { moviesState ->
        MovieScreenUIState(
            moviesState = moviesState,
            favourites = favouritesMoviesUseCase().cachedIn(viewModelScope),
            recentlyBrowsed = getRecentlyBrowsedMoviesUseCase().cachedIn(viewModelScope)
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, MovieScreenUIState.default)
}