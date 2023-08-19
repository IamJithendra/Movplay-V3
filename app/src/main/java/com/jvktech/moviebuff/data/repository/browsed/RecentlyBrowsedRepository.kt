package com.jvktech.moviebuff.data.repository.browsed

import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.movie.MovieDetails
import com.jvktech.moviebuff.data.model.movie.RecentlyBrowsedMovie
import com.jvktech.moviebuff.data.model.tvshow.RecentlyBrowsedTvShow
import com.jvktech.moviebuff.data.model.tvshow.TvShowDetails
import kotlinx.coroutines.flow.Flow

interface RecentlyBrowsedRepository {
    fun addRecentlyBrowsedMovie(movieDetails: MovieDetails)

    fun clearRecentlyBrowsedMovies()

    fun clearRecentlyBrowsedTvShows()

    fun recentlyBrowsedMovies(): Flow<PagingData<RecentlyBrowsedMovie>>

    fun addRecentlyBrowsedTvShows(tvShowDetails: TvShowDetails)

    fun recentlyBrowsedTvShows(): Flow<PagingData<RecentlyBrowsedTvShow>>
}