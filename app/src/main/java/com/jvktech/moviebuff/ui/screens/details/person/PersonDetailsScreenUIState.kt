package com.jvktech.moviebuff.ui.screens.details.person

import androidx.compose.runtime.Stable
import com.jvktech.moviebuff.data.model.CombinedCredits
import com.jvktech.moviebuff.data.model.ExternalId
import com.jvktech.moviebuff.data.model.PersonDetails

@Stable
data class PersonDetailsScreenUIState(
    val startRoute: String,
    val details: PersonDetails?,
    val externalIds: List<ExternalId>?,
    val credits: CombinedCredits?,
    val error: String?
) {
    companion object {
        fun getDefault(startRoute: String): PersonDetailsScreenUIState {
            return PersonDetailsScreenUIState(
                startRoute = startRoute,
                details = null,
                externalIds = null,
                credits = null,
                error = null
            )
        }
    }
}

@Stable
sealed class PersonDetailsState {
    object Loading : PersonDetailsState()
    object Error : PersonDetailsState()
    data class Result(val details: PersonDetails) : PersonDetailsState()
}