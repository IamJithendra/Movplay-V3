package com.jvktech.moviebuff.domain.usecase.tvshow

import com.jvktech.moviebuff.data.model.tvshow.TvShowDetails
import com.jvktech.moviebuff.data.repository.browsed.RecentlyBrowsedRepository
import javax.inject.Inject

class AddRecentlyBrowsedTvShowUseCaseImpl @Inject constructor(
    private val recentlyBrowsedRepository: RecentlyBrowsedRepository
) {
    operator fun invoke(details: TvShowDetails) {
        return recentlyBrowsedRepository.addRecentlyBrowsedTvShows(details)
    }
}