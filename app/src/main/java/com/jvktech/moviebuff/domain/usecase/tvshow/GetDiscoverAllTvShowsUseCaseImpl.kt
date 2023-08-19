package com.jvktech.moviebuff.domain.usecase.tvshow

import androidx.paging.PagingData
import androidx.paging.map
import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.data.model.Presentable
import com.jvktech.moviebuff.data.repository.tvshow.TvShowRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject


class GetDiscoverAllTvShowsUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(deviceLanguage: DeviceLanguage): Flow<PagingData<Presentable>> {
        return tvShowRepository.discoverTvShows(deviceLanguage)
            .mapLatest { data -> data.map { it } }

    }
}