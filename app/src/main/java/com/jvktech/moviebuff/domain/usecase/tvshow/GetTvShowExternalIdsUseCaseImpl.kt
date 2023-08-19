package com.jvktech.moviebuff.domain.usecase.tvshow

import com.jvktech.moviebuff.data.model.ExternalContentType
import com.jvktech.moviebuff.data.model.ExternalId
import com.jvktech.moviebuff.data.remote.api.ApiResponse
import com.jvktech.moviebuff.data.remote.api.awaitApiResponse
import com.jvktech.moviebuff.data.repository.tvshow.TvShowRepository
import javax.inject.Inject

class GetTvShowExternalIdsUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository
) {
    suspend operator fun invoke(tvShowId: Int): ApiResponse<List<ExternalId>> {
        val response = tvShowRepository.getExternalIds(tvShowId).awaitApiResponse()

        return when (response) {
            is ApiResponse.Success -> {
                val externalIds = response.data?.toExternalIdList(type = ExternalContentType.Tv)
                ApiResponse.Success(externalIds)
            }
            is ApiResponse.Failure -> ApiResponse.Failure(response.apiError)
            is ApiResponse.Exception -> ApiResponse.Exception(response.exception)
        }
    }
}