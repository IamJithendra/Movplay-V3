package com.jvktech.moviebuff.ui.screens.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jvktech.moviebuff.data.model.FavoriteType
import com.jvktech.moviebuff.domain.usecase.GetFavoritesUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchlistScreenViewModel @Inject constructor(
    private val getFavouritesUseCaseImpl: GetFavoritesUseCaseImpl
) : ViewModel() {
    private val _selectedFavouriteType: MutableStateFlow<FavoriteType> =
        MutableStateFlow(FavoriteType.Movie)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<WatchlistScreenUIState> = _selectedFavouriteType.mapLatest { type ->
        val favorites = getFavouritesUseCaseImpl(type).cachedIn(viewModelScope)

        WatchlistScreenUIState(
            selectedFavouriteType = type,
            favorites = favorites
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, WatchlistScreenUIState.default)

    fun onFavoriteTypeSelected(type: FavoriteType) {
        viewModelScope.launch {
            _selectedFavouriteType.emit(type)
        }
    }
}