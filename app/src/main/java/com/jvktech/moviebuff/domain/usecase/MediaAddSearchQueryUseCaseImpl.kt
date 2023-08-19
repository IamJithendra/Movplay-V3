package com.jvktech.moviebuff.domain.usecase

import com.jvktech.moviebuff.data.model.SearchQuery
import com.jvktech.moviebuff.data.repository.search.SearchRepository
import javax.inject.Inject

class MediaAddSearchQueryUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) {
    operator fun invoke(searchQuery: SearchQuery) {
        return searchRepository.addSearchQuery(searchQuery)
    }

}