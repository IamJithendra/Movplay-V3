package com.jvktech.moviebuff.domain.usecase.tvshow

import com.jvktech.moviebuff.data.model.tvshow.TvShowDetails
import com.jvktech.moviebuff.data.repository.favorites.FavoritesRepository
import javax.inject.Inject

class LikeTvShowUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke(details: TvShowDetails) {
        return favoritesRepository.likeTvShow(details)
    }
}