package com.jvktech.moviebuff.domain.usecase.tvshow

import com.jvktech.moviebuff.data.model.AggregatedCredits
import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.data.remote.api.ApiResponse
import com.jvktech.moviebuff.data.remote.api.awaitApiResponse
import com.jvktech.moviebuff.data.repository.season.SeasonRepository
import javax.inject.Inject

class GetSeasonCreditsUseCaseImpl @Inject constructor(
    private val seasonRepository: SeasonRepository
) {
    suspend operator fun invoke(
        tvShowId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<AggregatedCredits> {
        return seasonRepository.seasonCredits(
            tvShowId = tvShowId,
            seasonNumber = seasonNumber,
            isoCode = deviceLanguage.languageCode
        ).awaitApiResponse()
    }
}