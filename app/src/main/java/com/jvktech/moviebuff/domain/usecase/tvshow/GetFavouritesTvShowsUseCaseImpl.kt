package com.jvktech.moviebuff.domain.usecase.tvshow

import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.tvshow.TvShowFavourite
import com.jvktech.moviebuff.data.repository.favorites.FavouritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetFavouritesTvShowsUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavouritesRepository
) {
    operator fun invoke(): Flow<PagingData<TvShowFavourite>> {
        return favoritesRepository.favoriteTvShows()
    }
}