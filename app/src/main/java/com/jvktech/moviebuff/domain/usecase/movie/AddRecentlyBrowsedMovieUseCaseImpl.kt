package com.jvktech.moviebuff.domain.usecase.movie

import com.jvktech.moviebuff.data.model.movie.MovieDetails
import com.jvktech.moviebuff.data.repository.browsed.RecentlyBrowsedRepository
import javax.inject.Inject

class AddRecentlyBrowsedMovieUseCaseImpl @Inject constructor(
    private val recentlyBrowsedRepository: RecentlyBrowsedRepository
) {
    operator fun invoke(details: MovieDetails) {
        return recentlyBrowsedRepository.addRecentlyBrowsedMovie(details)
    }
}
