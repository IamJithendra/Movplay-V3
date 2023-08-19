package com.jvktech.moviebuff.domain.usecase.movie

import com.jvktech.moviebuff.data.model.movie.MovieDetails
import com.jvktech.moviebuff.data.repository.favorites.FavoritesRepository
import javax.inject.Inject

class LikeMovieUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke(details: MovieDetails) {
        return favoritesRepository.likeMovie(details)
    }
}