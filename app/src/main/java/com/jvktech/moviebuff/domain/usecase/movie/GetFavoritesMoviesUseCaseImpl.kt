package com.jvktech.moviebuff.domain.usecase.movie

import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.movie.MovieFavorite
import com.jvktech.moviebuff.data.repository.favorites.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesMoviesUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke(): Flow<PagingData<MovieFavorite>> {
        return favoritesRepository.favoriteMovies()
    }
}