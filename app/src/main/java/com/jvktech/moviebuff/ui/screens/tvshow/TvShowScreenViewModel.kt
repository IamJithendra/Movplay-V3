package com.jvktech.moviebuff.ui.screens.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.domain.usecase.GetDeviceLanguageUseCaseImpl
import com.jvktech.moviebuff.domain.usecase.tvshow.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TvShowScreenViewModel @Inject constructor(
    private val getDeviceLanguageUseCase: GetDeviceLanguageUseCaseImpl,
    private val getOnTheAirTvShowsUseCase: GetOnTheAirTvShowsUseCaseImpl,
    private val getDiscoverAllTvShowsUseCase: GetDiscoverAllTvShowsUseCaseImpl,
    private val getTopRatedTvShowsUseCase: GetTopRatedTvShowsUseCaseImpl,
    private val getTrendingTvShowsUseCase: GetTrendingTvShowsUseCaseImpl,
    private val getAiringTodayTvShowsUseCase: GetAiringTodayTvShowsUseCaseImpl,
    private val getFavouritesTvShowsUseCase: GetFavouritesTvShowsUseCaseImpl,
    private val getRecentlyBrowsedTvShowsUseCase: GetRecentlyBrowsedTvShowsUseCaseImpl
) : ViewModel() {
    private val deviceLanguage: Flow<DeviceLanguage> = getDeviceLanguageUseCase()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val tvShowsState: StateFlow<TvShowsState> = deviceLanguage.mapLatest { deviceLanguage ->
        TvShowsState(
            onTheAir = getOnTheAirTvShowsUseCase(deviceLanguage, true)
                .cachedIn(viewModelScope),
            discover = getDiscoverAllTvShowsUseCase(deviceLanguage)
                .cachedIn(viewModelScope),
            topRated = getTopRatedTvShowsUseCase(deviceLanguage)
                .cachedIn(viewModelScope),
            trending = getTrendingTvShowsUseCase(deviceLanguage)
                .cachedIn(viewModelScope),
            airingToday = getAiringTodayTvShowsUseCase(deviceLanguage)
                .cachedIn(viewModelScope)
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(10), TvShowsState.default)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<TvShowScreenUIState> = tvShowsState.mapLatest { tvShowsState ->
        TvShowScreenUIState(
            tvShowsState = tvShowsState,
            favorites = getFavouritesTvShowsUseCase(),
            recentlyBrowsed = getRecentlyBrowsedTvShowsUseCase()
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, TvShowScreenUIState.default)
}