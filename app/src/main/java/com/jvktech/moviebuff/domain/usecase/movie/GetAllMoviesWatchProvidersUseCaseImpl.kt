package com.jvktech.moviebuff.domain.usecase.movie

import com.jvktech.moviebuff.data.model.ProviderSource
import com.jvktech.moviebuff.data.repository.config.ConfigRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMoviesWatchProvidersUseCaseImpl @Inject constructor(
    private val configRepository: ConfigRepository
) {
    operator fun invoke(): Flow<List<ProviderSource>> {
        return configRepository.getAllMoviesWatchProviders()
    }
}