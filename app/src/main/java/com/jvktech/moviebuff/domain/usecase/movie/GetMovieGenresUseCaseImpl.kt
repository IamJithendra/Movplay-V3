package com.jvktech.moviebuff.domain.usecase.movie

import com.jvktech.moviebuff.data.model.Genre
import com.jvktech.moviebuff.data.repository.config.ConfigRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetMovieGenresUseCaseImpl @Inject constructor(
    private val configRepository: ConfigRepository
) {
    operator fun invoke(): Flow<List<Genre>> {
        return configRepository.getMoviesGenres()
    }
}