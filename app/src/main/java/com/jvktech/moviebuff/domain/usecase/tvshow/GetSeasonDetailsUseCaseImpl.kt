package com.jvktech.moviebuff.domain.usecase.tvshow

import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.data.model.SeasonDetails
import com.jvktech.moviebuff.data.remote.api.ApiResponse
import com.jvktech.moviebuff.data.remote.api.awaitApiResponse
import com.jvktech.moviebuff.data.repository.season.SeasonRepository
import javax.inject.Inject

class GetSeasonDetailsUseCaseImpl @Inject constructor(
    private val seasonRepository: SeasonRepository
) {
    suspend operator fun invoke(
        tvShowId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<SeasonDetails> {
        return seasonRepository.seasonDetails(
            tvShowId = tvShowId,
            seasonNumber = seasonNumber,
            deviceLanguage = deviceLanguage
        ).awaitApiResponse()
    }
}