package com.jvktech.moviebuff.domain.usecase.tvshow

import com.jvktech.moviebuff.data.repository.favorites.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteTvShowsCountUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke(): Flow<Int> {
        return favoritesRepository.getFavoriteTvShowsCount()
    }
}