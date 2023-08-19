package com.jvktech.moviebuff.domain.usecase

import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.data.model.SearchResult
import com.jvktech.moviebuff.data.repository.search.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMediaMultiSearchUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) {
    operator fun invoke(
        query: String,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<SearchResult>> {
        return searchRepository.multiSearch(query = query, deviceLanguage = deviceLanguage)
    }

}