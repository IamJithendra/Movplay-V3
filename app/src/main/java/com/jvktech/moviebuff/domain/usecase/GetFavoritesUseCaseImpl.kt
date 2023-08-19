package com.jvktech.moviebuff.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.jvktech.moviebuff.data.model.FavoriteType
import com.jvktech.moviebuff.data.model.Presentable
import com.jvktech.moviebuff.data.repository.favorites.FavoritesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject


class GetFavoritesUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(type: FavoriteType): Flow<PagingData<Presentable>> {
        val favorites = when (type) {
            FavoriteType.Movie -> favoritesRepository.favoriteMovies()
            FavoriteType.TvShow -> favoritesRepository.favoriteTvShows()
        }.mapLatest { data -> data.map { it } }

        return favorites
    }
}