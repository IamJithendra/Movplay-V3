package com.jvktech.moviebuff.domain.usecase.movie

import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.movie.RecentlyBrowsedMovie
import com.jvktech.moviebuff.data.repository.browsed.RecentlyBrowsedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentlyBrowsedMoviesUseCaseImpl @Inject constructor(
    private val recentlyBrowsedRepository: RecentlyBrowsedRepository,
) {
    operator fun invoke(): Flow<PagingData<RecentlyBrowsedMovie>> {
        return recentlyBrowsedRepository.recentlyBrowsedMovies()
    }
}