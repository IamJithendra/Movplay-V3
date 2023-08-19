package com.jvktech.moviebuff.ui.screens.browse.tvshows

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.Presentable
import com.jvktech.moviebuff.data.model.tvshow.TvShowType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class BrowseTvShowsScreenUIState(
    val selectedTvShowType: TvShowType,
    val tvShow: Flow<PagingData<Presentable>>,
    val favoriteTvShowsCount: Int
) {
    companion object {
        fun getDefault(selectedTvShowType: TvShowType): BrowseTvShowsScreenUIState {
            return BrowseTvShowsScreenUIState(
                selectedTvShowType = selectedTvShowType,
                tvShow = emptyFlow(),
                favoriteTvShowsCount = 0
            )
        }
    }
}