package com.jvktech.moviebuff.domain.usecase.tvshow

import com.jvktech.moviebuff.data.model.Image
import com.jvktech.moviebuff.data.remote.api.ApiResponse
import com.jvktech.moviebuff.data.remote.api.awaitApiResponse
import com.jvktech.moviebuff.data.repository.tvshow.TvShowRepository
import javax.inject.Inject

class GetTvShowImagesUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository
) {
    suspend operator fun invoke(tvShowId: Int): ApiResponse<List<Image>> {
        val response = tvShowRepository.tvShowImages(tvShowId).awaitApiResponse()

        return when (response) {
            is ApiResponse.Success -> {
                val images = response.data?.backdrops ?: emptyList()
                ApiResponse.Success(images)
            }
            is ApiResponse.Failure -> ApiResponse.Failure(response.apiError)
            is ApiResponse.Exception -> ApiResponse.Exception(response.exception)
        }
    }
}