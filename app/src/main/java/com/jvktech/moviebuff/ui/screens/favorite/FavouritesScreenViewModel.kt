package com.jvktech.moviebuff.ui.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jvktech.moviebuff.data.model.FavouriteType
import com.jvktech.moviebuff.domain.usecase.GetFavoritesUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesScreenViewModel @Inject constructor(
    private val getFavouritesUseCaseImpl: GetFavoritesUseCaseImpl
) : ViewModel() {
    private val _selectedFavouriteType: MutableStateFlow<FavouriteType> =
        MutableStateFlow(FavouriteType.Movie)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<FavouritesScreenUIState> = _selectedFavouriteType.mapLatest { type ->
        val favorites = getFavouritesUseCaseImpl(type).cachedIn(viewModelScope)

        FavouritesScreenUIState(
            selectedFavouriteType = type,
            favorites = favorites
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, FavouritesScreenUIState.default)

    fun onFavoriteTypeSelected(type: FavouriteType) {
        viewModelScope.launch {
            _selectedFavouriteType.emit(type)
        }
    }
}