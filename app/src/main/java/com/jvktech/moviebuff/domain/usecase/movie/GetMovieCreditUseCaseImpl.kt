package com.jvktech.moviebuff.domain.usecase.movie

import com.jvktech.moviebuff.data.model.Credits
import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.data.remote.api.ApiResponse
import com.jvktech.moviebuff.data.remote.api.awaitApiResponse
import com.jvktech.moviebuff.data.repository.movie.MovieRepository
import javax.inject.Inject

class GetMovieCreditUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(
        movieId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<Credits> {
        return movieRepository.movieCredits(
            movieId = movieId,
            isoCode = deviceLanguage.languageCode
        ).awaitApiResponse()
    }

}