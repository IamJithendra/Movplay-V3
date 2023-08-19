package com.jvktech.moviebuff.domain.usecase.movie

import com.jvktech.moviebuff.data.remote.api.ApiResponse
import com.jvktech.moviebuff.data.remote.api.awaitApiResponse
import com.jvktech.moviebuff.data.repository.movie.MovieRepository
import javax.inject.Inject

class GetMovieReviewsCountUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): ApiResponse<Int> {
        val response = movieRepository
            .movieReview(movieId)
            .awaitApiResponse()

        return when (response) {
            is ApiResponse.Success -> {
                val reviewsCount = response.data?.totalResults ?: 0
                ApiResponse.Success(reviewsCount)
            }
            is ApiResponse.Failure -> ApiResponse.Failure(response.apiError)
            is ApiResponse.Exception -> ApiResponse.Exception(response.exception)
        }
    }
}