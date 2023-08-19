package com.jvktech.moviebuff.domain.usecase.movie

import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.data.model.WatchProviders
import com.jvktech.moviebuff.data.remote.api.ApiResponse
import com.jvktech.moviebuff.data.remote.api.awaitApiResponse
import com.jvktech.moviebuff.data.repository.movie.MovieRepository
import javax.inject.Inject

class GetMovieWatchProvidersUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(
        movieId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<WatchProviders?> {
        val response = movieRepository.watchProviders(
            movieId = movieId
        ).awaitApiResponse()

        return when (response) {
            is ApiResponse.Success -> {
                val results = response.data?.results
                val watchProviders = results?.getOrElse(deviceLanguage.region) { null }
                ApiResponse.Success(watchProviders)
            }
            is ApiResponse.Failure -> ApiResponse.Failure(response.apiError)
            is ApiResponse.Exception -> ApiResponse.Exception(response.exception)
        }
    }

}