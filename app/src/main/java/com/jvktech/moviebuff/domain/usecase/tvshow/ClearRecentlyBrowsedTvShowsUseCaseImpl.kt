package com.jvktech.moviebuff.domain.usecase.tvshow

import com.jvktech.moviebuff.data.repository.browsed.RecentlyBrowsedRepository
import javax.inject.Inject

class ClearRecentlyBrowsedTvShowsUseCaseImpl @Inject constructor(
    private val recentlyBrowsedRepository: RecentlyBrowsedRepository
) {
    operator fun invoke() {
        return recentlyBrowsedRepository.clearRecentlyBrowsedTvShows()
    }
}