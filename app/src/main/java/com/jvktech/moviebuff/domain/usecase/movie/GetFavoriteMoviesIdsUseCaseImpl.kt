package com.jvktech.moviebuff.domain.usecase.movie

import com.jvktech.moviebuff.data.repository.favorites.FavouritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMoviesIdsUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavouritesRepository
) {
    operator fun invoke(): Flow<List<Int>> {
        return favoritesRepository.getFavoriteMoviesIds()
    }
}