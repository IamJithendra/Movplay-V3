package com.jvktech.moviebuff.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.jvktech.moviebuff.data.model.FavouriteType
import com.jvktech.moviebuff.data.model.Presentable
import com.jvktech.moviebuff.data.repository.favorites.FavouritesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject


class GetFavoritesUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavouritesRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(type: FavouriteType): Flow<PagingData<Presentable>> {
        val favorites = when (type) {
            FavouriteType.Movie -> favoritesRepository.favoriteMovies()
            FavouriteType.TvShow -> favoritesRepository.favoriteTvShows()
        }.mapLatest { data -> data.map { it } }

        return favorites
    }
}