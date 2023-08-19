package com.jvktech.moviebuff.domain.usecase.tvshow

import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.data.model.tvshow.TvShowDetails
import com.jvktech.moviebuff.data.remote.api.ApiResponse
import com.jvktech.moviebuff.data.remote.api.awaitApiResponse
import com.jvktech.moviebuff.data.repository.tvshow.TvShowRepository
import javax.inject.Inject


class GetTvShowDetailsUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository
) {
    suspend operator fun invoke(
        tvShowId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<TvShowDetails> {
        return tvShowRepository.getTvShowDetails(
            tvShowId = tvShowId,
            deviceLanguage = deviceLanguage
        ).awaitApiResponse()
    }

}