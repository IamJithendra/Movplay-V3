package com.jvktech.moviebuff.domain.usecase.movie

import com.jvktech.moviebuff.data.repository.browsed.RecentlyBrowsedRepository
import javax.inject.Inject

class ClearRecentlyBrowsedMoviesUseCaseImpl @Inject constructor(
    private val recentlyBrowsedRepository: RecentlyBrowsedRepository
) {
    operator fun invoke() {
        return recentlyBrowsedRepository.clearRecentlyBrowsedMovies()
    }
}