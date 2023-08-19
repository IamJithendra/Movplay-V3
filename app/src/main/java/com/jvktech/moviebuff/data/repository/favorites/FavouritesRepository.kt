package com.jvktech.moviebuff.data.repository.favorites

import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.movie.MovieDetails
import com.jvktech.moviebuff.data.model.movie.MovieFavourite
import com.jvktech.moviebuff.data.model.tvshow.TvShowDetails
import com.jvktech.moviebuff.data.model.tvshow.TvShowFavourite
import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {
    fun likeMovie(movieDetails: MovieDetails)

    fun likeTvShow(tvShowDetails: TvShowDetails)

    fun unlikeMovie(movieDetails: MovieDetails)

    fun unlikeTvShows(tvShowDetails: TvShowDetails)

    fun favoriteMovies(): Flow<PagingData<MovieFavourite>>

    fun favoriteTvShows(): Flow<PagingData<TvShowFavourite>>

    fun getFavoriteMoviesIds(): Flow<List<Int>>

    fun getFavoriteTvShowsIds(): Flow<List<Int>>

    fun getFavoriteMoviesCount(): Flow<Int>

    fun getFavoriteTvShowsCount(): Flow<Int>
}