package com.jvktech.moviebuff.domain.usecase.tvshow

import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.tvshow.TvShowFavorite
import com.jvktech.moviebuff.data.repository.favorites.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetFavoritesTvShowsUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke(): Flow<PagingData<TvShowFavorite>> {
        return favoritesRepository.favoriteTvShows()
    }
}