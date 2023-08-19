package com.jvktech.moviebuff.domain.usecase.tvshow

import com.jvktech.moviebuff.data.remote.api.ApiResponse
import com.jvktech.moviebuff.data.remote.api.awaitApiResponse
import com.jvktech.moviebuff.data.repository.tvshow.TvShowRepository
import javax.inject.Inject

class GetTvShowReviewsCountUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository
) {
    suspend operator fun invoke(tvShowId: Int): ApiResponse<Int> {
        val response = tvShowRepository.tvShowReview(tvShowId).awaitApiResponse()

        return when (response) {
            is ApiResponse.Success -> {
                val reviewCount = response.data?.totalResults ?: 0
                ApiResponse.Success(reviewCount)
            }
            is ApiResponse.Failure -> ApiResponse.Failure(response.apiError)
            is ApiResponse.Exception -> ApiResponse.Exception(response.exception)
        }
    }
}