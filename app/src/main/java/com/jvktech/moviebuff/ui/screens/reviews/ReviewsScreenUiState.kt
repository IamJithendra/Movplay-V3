package com.jvktech.moviebuff.ui.screens.reviews

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.Review
import com.jvktech.moviebuff.ui.screens.destinations.HomeScreenDestination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class ReviewsScreenUiState(
    val startRoute: String,
    val reviews: Flow<PagingData<Review>>
) {
    companion object {
        val default: ReviewsScreenUiState = ReviewsScreenUiState(
            startRoute = HomeScreenDestination.route,
            reviews = emptyFlow()
        )
    }
}