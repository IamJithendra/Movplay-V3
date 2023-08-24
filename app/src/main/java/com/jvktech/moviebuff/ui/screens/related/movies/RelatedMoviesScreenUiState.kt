package com.jvktech.moviebuff.ui.screens.related.movies

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.RelationType
import com.jvktech.moviebuff.data.model.movie.Movie
import com.jvktech.moviebuff.ui.screens.destinations.HomeScreenDestination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class RelatedMoviesScreenUiState(
    val relationType: RelationType,
    val movies: Flow<PagingData<Movie>>,
    val startRoute: String
) {
    companion object {
        fun getDefault(relationType: RelationType): RelatedMoviesScreenUiState {
            return RelatedMoviesScreenUiState(
                relationType = relationType,
                movies = emptyFlow(),
                startRoute = HomeScreenDestination.route
            )
        }
    }
}