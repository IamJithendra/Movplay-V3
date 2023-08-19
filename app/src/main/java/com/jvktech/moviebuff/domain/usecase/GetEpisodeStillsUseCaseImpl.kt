package com.jvktech.moviebuff.domain.usecase

import com.jvktech.moviebuff.data.model.Image
import com.jvktech.moviebuff.data.remote.api.ApiResponse
import com.jvktech.moviebuff.data.remote.api.awaitApiResponse
import com.jvktech.moviebuff.data.repository.season.SeasonRepository
import javax.inject.Inject

class GetEpisodeStillsUseCaseImpl @Inject constructor(
    private val seasonRepository: SeasonRepository
) {
    suspend operator fun invoke(
        tvShowId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): ApiResponse<List<Image>> {
        val response = seasonRepository.episodesImage(
            tvShowId = tvShowId, seasonNumber = seasonNumber, episodeNumber = episodeNumber
        ).awaitApiResponse()

        return when (response) {
            is ApiResponse.Success -> {
                val stills = response.data?.stills ?: emptyList()
                ApiResponse.Success(stills)
            }
            is ApiResponse.Failure -> ApiResponse.Failure(response.apiError)
            is ApiResponse.Exception -> ApiResponse.Exception(response.exception)
        }
    }

}