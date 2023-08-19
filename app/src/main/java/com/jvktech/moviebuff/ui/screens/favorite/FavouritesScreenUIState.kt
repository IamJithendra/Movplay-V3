package com.jvktech.moviebuff.ui.screens.favorite

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.FavouriteType
import com.jvktech.moviebuff.data.model.Presentable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class FavouritesScreenUIState(
    val selectedFavouriteType: FavouriteType,
    val favorites: Flow<PagingData<Presentable>>,
) {
    companion object {
        val default: FavouritesScreenUIState = FavouritesScreenUIState(
            selectedFavouriteType = FavouriteType.Movie,
            favorites = emptyFlow()
        )
    }
}