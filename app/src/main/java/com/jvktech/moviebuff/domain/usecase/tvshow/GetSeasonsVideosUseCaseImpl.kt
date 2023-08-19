package com.jvktech.moviebuff.domain.usecase.tvshow

import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.data.model.Video
import com.jvktech.moviebuff.data.remote.api.ApiResponse
import com.jvktech.moviebuff.data.remote.api.awaitApiResponse
import com.jvktech.moviebuff.data.repository.season.SeasonRepository
import javax.inject.Inject


class GetSeasonsVideosUseCaseImpl @Inject constructor(
    private val seasonRepository: SeasonRepository
) {
    suspend operator fun invoke(
        tvShowId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<List<Video>> {
        val response = seasonRepository.seasonVideos(
            tvShowId = tvShowId,
            seasonNumber = seasonNumber,
        ).awaitApiResponse()

        return when (response) {
            is ApiResponse.Success -> {
                val videos = response.data?.results?.sortedWith(
                    compareBy<Video> { video ->
                        video.language == deviceLanguage.languageCode
                    }.thenByDescending { video ->
                        video.publishedAt
                    }
                )
                ApiResponse.Success(videos)
            }
            is ApiResponse.Failure -> ApiResponse.Failure(response.apiError)
            is ApiResponse.Exception -> ApiResponse.Exception(response.exception)
        }
    }
}