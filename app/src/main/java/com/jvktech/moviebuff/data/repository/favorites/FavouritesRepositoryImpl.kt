package com.jvktech.moviebuff.data.repository.favorites

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jvktech.moviebuff.data.local.db.movie.FavouritesMoviesDao
import com.jvktech.moviebuff.data.local.db.tvshow.FavoritesTvShowsDao
import com.jvktech.moviebuff.data.model.movie.MovieDetails
import com.jvktech.moviebuff.data.model.movie.MovieFavourite
import com.jvktech.moviebuff.data.model.tvshow.TvShowDetails
import com.jvktech.moviebuff.data.model.tvshow.TvShowFavourite
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavouritesRepositoryImpl @Inject constructor(
    private val externalScope: CoroutineScope,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val favoritesMoviesDao: FavouritesMoviesDao,
    private val favoritesTvShowsDao: FavoritesTvShowsDao
) : FavouritesRepository {
    override fun likeMovie(movieDetails: MovieDetails) {
        externalScope.launch(defaultDispatcher) {
            val favoriteMovie = movieDetails.run {
                MovieFavourite(
                    id = id,
                    posterPath = posterPath,
                    title = title,
                    originalTitle = originalTitle,
                    addedDate = Date()
                )
            }
            favoritesMoviesDao.likeMovie(favoriteMovie)
        }
    }

    override fun likeTvShow(tvShowDetails: TvShowDetails) {
        externalScope.launch(defaultDispatcher) {
            val favoriteTvShow = tvShowDetails.run {
                TvShowFavourite(
                    id = id,
                    posterPath = posterPath,
                    name = name,
                    addedDate = Date()
                )
            }
            favoritesTvShowsDao.likeTvShow(favoriteTvShow)
        }
    }

    override fun unlikeMovie(movieDetails: MovieDetails) {
        externalScope.launch {
            favoritesMoviesDao.unlikeMovie(movieDetails.id)
        }
    }

    override fun unlikeTvShows(tvShowDetails: TvShowDetails) {
        externalScope.launch {
            favoritesTvShowsDao.unlikeTvShow(tvShowDetails.id)
        }
    }

    override fun favoriteMovies(): Flow<PagingData<MovieFavourite>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        favoritesMoviesDao.getAllFavoriteMovies().asPagingSourceFactory()()
    }.flow.flowOn(defaultDispatcher)

    override fun favoriteTvShows(): Flow<PagingData<TvShowFavourite>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        favoritesTvShowsDao.getAllFavoriteTvShows().asPagingSourceFactory()()
    }.flow.flowOn(defaultDispatcher)

    override fun getFavoriteMoviesIds(): Flow<List<Int>> {
        return favoritesMoviesDao.favouriteMoviesIds().distinctUntilChanged()
    }

    override fun getFavoriteTvShowsIds(): Flow<List<Int>> {
        return favoritesTvShowsDao.favoriteTvShowIds().distinctUntilChanged()
    }

    override fun getFavoriteMoviesCount(): Flow<Int> {
        return favoritesMoviesDao.favouriteMoviesCount().distinctUntilChanged()
    }

    override fun getFavoriteTvShowsCount(): Flow<Int> {
        return favoritesTvShowsDao.favoriteTvShowCount().distinctUntilChanged()
    }
}