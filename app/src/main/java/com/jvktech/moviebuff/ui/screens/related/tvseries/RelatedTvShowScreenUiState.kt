package com.jvktech.moviebuff.ui.screens.related.tvseries

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.RelationType
import com.jvktech.moviebuff.data.model.tvshow.TvShow
import com.jvktech.moviebuff.ui.screens.destinations.HomeScreenDestination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class RelatedTvShowScreenUiState(
    val relationType: RelationType,
    val tvShow: Flow<PagingData<TvShow>>,
    val startRoute: String
) {
    companion object {
        fun getDefault(relationType: RelationType): RelatedTvShowScreenUiState {
            return RelatedTvShowScreenUiState(
                relationType = relationType,
                tvShow = emptyFlow(),
                startRoute = HomeScreenDestination.route
            )
        }
    }
}