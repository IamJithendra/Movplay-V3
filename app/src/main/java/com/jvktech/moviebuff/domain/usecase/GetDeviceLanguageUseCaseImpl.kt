package com.jvktech.moviebuff.domain.usecase

import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.data.repository.config.ConfigRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetDeviceLanguageUseCaseImpl @Inject constructor(
    private val configRepository: ConfigRepository
) {
    operator fun invoke(): Flow<DeviceLanguage> {
        return configRepository.getDeviceLanguage()
    }
}