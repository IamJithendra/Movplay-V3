package com.jvktech.moviebuff.ui.screens.seasons

import androidx.compose.runtime.Stable
import com.jvktech.moviebuff.data.model.AggregatedCredits
import com.jvktech.moviebuff.data.model.Image
import com.jvktech.moviebuff.data.model.SeasonDetails
import com.jvktech.moviebuff.data.model.Video
import com.jvktech.moviebuff.ui.screens.destinations.TvShowScreenDestination

@Stable
data class SeasonDetailsScreenUiState(
    val startRoute: String,
    val seasonDetails: SeasonDetails?,
    val aggregatedCredits: AggregatedCredits?,
    val videos: List<Video>?,
    val episodeCount: Int?,
    val episodeStills: Map<Int, List<Image>>,
    val error: String?
) {
    companion object {
        val default: SeasonDetailsScreenUiState = SeasonDetailsScreenUiState(
            startRoute = TvShowScreenDestination.route,
            seasonDetails = null,
            aggregatedCredits = null,
            videos = null,
            episodeCount = null,
            episodeStills = emptyMap(),
            error = null
        )
    }
}