package com.jvktech.moviebuff.domain.usecase.movie

import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.movie.MovieFavourite
import com.jvktech.moviebuff.data.repository.favorites.FavouritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteMoviesUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavouritesRepository
) {
    operator fun invoke(): Flow<PagingData<MovieFavourite>> {
        return favoritesRepository.favoriteMovies()
    }
}