package com.jvktech.moviebuff.ui.screens.watchlist

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.FavoriteType
import com.jvktech.moviebuff.data.model.Presentable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class WatchlistScreenUIState(
    val selectedFavouriteType: FavoriteType,
    val favorites: Flow<PagingData<Presentable>>,
) {
    companion object {
        val default: WatchlistScreenUIState = WatchlistScreenUIState(
            selectedFavouriteType = FavoriteType.Movie,
            favorites = emptyFlow()
        )
    }
}