package com.jvktech.moviebuff.data.repository.person

import com.jvktech.moviebuff.data.model.CombinedCredits
import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.data.model.ExternalIds
import com.jvktech.moviebuff.data.model.PersonDetails
import retrofit2.Call

interface PersonRepository {
    fun getPersonDetails(
        personId: Int,
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ) : Call<PersonDetails>

    fun getCombinedCredits(
        personId: Int,
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ) : Call<CombinedCredits>

    fun getExternalIds(
        personId: Int,
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Call<ExternalIds>
}