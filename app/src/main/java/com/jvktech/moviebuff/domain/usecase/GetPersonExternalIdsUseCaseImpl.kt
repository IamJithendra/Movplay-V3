package com.jvktech.moviebuff.domain.usecase

import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.data.model.ExternalIds
import com.jvktech.moviebuff.data.remote.api.ApiResponse
import com.jvktech.moviebuff.data.remote.api.awaitApiResponse
import com.jvktech.moviebuff.data.repository.person.PersonRepository
import javax.inject.Inject

class GetPersonExternalIdsUseCaseImpl @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend operator fun invoke(
        personId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<ExternalIds> {
        return personRepository.getExternalIds(personId = personId, deviceLanguage = deviceLanguage)
            .awaitApiResponse()
    }

}