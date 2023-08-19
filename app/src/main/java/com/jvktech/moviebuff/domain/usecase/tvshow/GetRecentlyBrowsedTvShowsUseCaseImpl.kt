package com.jvktech.moviebuff.domain.usecase.tvshow

import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.tvshow.RecentlyBrowsedTvShow
import com.jvktech.moviebuff.data.repository.browsed.RecentlyBrowsedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentlyBrowsedTvShowsUseCaseImpl @Inject constructor(
    private val recentlyBrowsedRepository: RecentlyBrowsedRepository
) {
    operator fun invoke(): Flow<PagingData<RecentlyBrowsedTvShow>> {
        return recentlyBrowsedRepository.recentlyBrowsedTvShows()
    }
}