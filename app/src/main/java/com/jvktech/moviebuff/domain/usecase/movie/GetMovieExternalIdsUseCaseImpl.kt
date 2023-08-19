package com.jvktech.moviebuff.domain.usecase.movie

import com.jvktech.moviebuff.data.model.ExternalContentType
import com.jvktech.moviebuff.data.model.ExternalId
import com.jvktech.moviebuff.data.remote.api.ApiResponse
import com.jvktech.moviebuff.data.remote.api.awaitApiResponse
import com.jvktech.moviebuff.data.repository.movie.MovieRepository
import javax.inject.Inject


class GetMovieExternalIdsUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): ApiResponse<List<ExternalId>> {
        val response = movieRepository.getExternalIds(movieId).awaitApiResponse()

        return when (response) {
            is ApiResponse.Success -> {
                val ids = response.data?.toExternalIdList(ExternalContentType.Movie)
                ApiResponse.Success(ids)
            }
            is ApiResponse.Failure -> ApiResponse.Failure(response.apiError)
            is ApiResponse.Exception -> ApiResponse.Exception(response.exception)
        }
    }
}