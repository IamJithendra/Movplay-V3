package com.jvktech.moviebuff.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jvktech.moviebuff.data.local.db.movie.*
import com.jvktech.moviebuff.data.local.db.tvshow.*
import com.jvktech.moviebuff.data.model.SearchQuery
import com.jvktech.moviebuff.data.model.movie.*
import com.jvktech.moviebuff.data.model.tvshow.*
import com.jvktech.moviebuff.utils.DateConverters

@Database(
    entities = [
        MovieFavorite::class,
        TvShowFavorite::class,
        RecentlyBrowsedMovie::class,
        RecentlyBrowsedTvShow::class,
        SearchQuery::class,
        MovieEntity::class,
        TvShowEntity::class,
        MoviesRemoteKeys::class,
        TvShowsRemoteKeys::class,
        MovieDetailEntity::class,
        TvShowDetailEntity::class,
        MovieDetailsRemoteKey::class,
        TvShowDetailsRemoteKey::class
    ],
    version = 1
)
@TypeConverters(DateConverters::class)
abstract class AppDatabase : RoomDatabase() {
    //Movies
    abstract fun moviesDao(): MoviesDao
    abstract fun favoritesMoviesDao(): FavoritesMoviesDao
    abstract fun recentlyBrowsedMovies(): RecentlyBrowsedMoviesDao
    abstract fun moviesRemoteKeysDao(): MoviesRemoteKeysDao
    abstract fun moviesDetailsDao(): MoviesDetailsDao
    abstract fun moviesDetailsRemoteKeys(): MoviesDetailsRemoteKeysDao

    //Tv Shows
    abstract fun tvShowsDao(): TvShowsDao
    abstract fun favoritesTvShowDao(): FavoritesTvShowsDao
    abstract fun recentlyBrowsedTvShows(): RecentlyBrowsedTvShowsDao
    abstract fun tvShowsRemoteKeysDao(): TvShowsRemoteKeysDao
    abstract fun tvShowsDetailsDao(): TvShowsDetailsDao
    abstract fun tvShowsDetailsRemoteKeys(): TvShowsDetailsRemoteKeysDao

    abstract fun searchQueryDao(): SearchQueryDao
}