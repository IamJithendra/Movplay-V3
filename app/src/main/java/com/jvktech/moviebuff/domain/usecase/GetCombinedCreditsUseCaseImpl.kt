package com.jvktech.moviebuff.domain.usecase

import com.jvktech.moviebuff.data.model.CombinedCredits
import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.data.remote.api.ApiResponse
import com.jvktech.moviebuff.data.remote.api.awaitApiResponse
import com.jvktech.moviebuff.data.repository.person.PersonRepository
import javax.inject.Inject

class GetCombinedCreditsUseCaseImpl @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend operator fun invoke(
        personId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<CombinedCredits> {
        return personRepository.getCombinedCredits(
            personId = personId,
            deviceLanguage = deviceLanguage
        ).awaitApiResponse()
    }
}