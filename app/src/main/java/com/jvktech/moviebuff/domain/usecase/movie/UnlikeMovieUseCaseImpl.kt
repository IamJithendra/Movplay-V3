package com.jvktech.moviebuff.domain.usecase.movie

import com.jvktech.moviebuff.data.model.movie.MovieDetails
import com.jvktech.moviebuff.data.repository.favorites.FavouritesRepository
import javax.inject.Inject

class UnlikeMovieUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavouritesRepository
) {
    operator fun invoke(details: MovieDetails) {
        return favoritesRepository.unlikeMovie(details)
    }
}