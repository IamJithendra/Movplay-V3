package com.jvktech.moviebuff.di

import android.content.Context
import com.jvktech.moviebuff.data.paging.ConfigDataSource
import com.jvktech.moviebuff.data.remote.api.movie.TmdbMoviesApiHelper
import com.jvktech.moviebuff.data.remote.api.others.TmdbOthersApiHelper
import com.jvktech.moviebuff.data.remote.api.tvshow.TmdbTvShowsApiHelper
import com.jvktech.moviebuff.data.repository.browsed.RecentlyBrowsedRepository
import com.jvktech.moviebuff.data.repository.browsed.RecentlyBrowsedRepositoryImpl
import com.jvktech.moviebuff.data.repository.config.ConfigRepository
import com.jvktech.moviebuff.data.repository.config.ConfigRepositoryImpl
import com.jvktech.moviebuff.data.repository.favorites.FavouritesRepository
import com.jvktech.moviebuff.data.repository.favorites.FavouritesRepositoryImpl
import com.jvktech.moviebuff.data.repository.movie.MovieRepository
import com.jvktech.moviebuff.data.repository.movie.MovieRepositoryImpl
import com.jvktech.moviebuff.data.repository.person.PersonRepository
import com.jvktech.moviebuff.data.repository.person.PersonRepositoryImpl
import com.jvktech.moviebuff.data.repository.search.SearchRepository
import com.jvktech.moviebuff.data.repository.search.SearchRepositoryImpl
import com.jvktech.moviebuff.data.repository.season.SeasonRepository
import com.jvktech.moviebuff.data.repository.season.SeasonRepositoryImpl
import com.jvktech.moviebuff.data.repository.tvshow.TvShowRepository
import com.jvktech.moviebuff.data.repository.tvshow.TvShowRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideConfigDataSource(
        @ApplicationContext context: Context,
        externalScope: CoroutineScope,
        dispatcher: CoroutineDispatcher,
        apiOtherHelper: TmdbOthersApiHelper,
        apiMovieHelper: TmdbMoviesApiHelper,
        apiTvShowHelper: TmdbTvShowsApiHelper
    ): ConfigDataSource = ConfigDataSource(
        context = context,
        externalScope = externalScope,
        defaultDispatcher = dispatcher,
        apiOtherHelper = apiOtherHelper,
        apiMovieHelper = apiMovieHelper,
        apiTvShowHelper = apiTvShowHelper
    )

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryBinds {
        @Binds
        @Singleton
        fun provideConfigRepository(impl: ConfigRepositoryImpl): ConfigRepository

        @Binds
        @Singleton
        fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

        @Binds
        @Singleton
        fun bindTvShowRepository(impl: TvShowRepositoryImpl): TvShowRepository

        @Binds
        @Singleton
        fun bindsBrowsedRepository(impl: RecentlyBrowsedRepositoryImpl): RecentlyBrowsedRepository

        @Binds
        @Singleton
        fun bindFavouritesRepository(impl: FavouritesRepositoryImpl): FavouritesRepository

        @Binds
        @Singleton
        fun bindPersonRepository(impl: PersonRepositoryImpl): PersonRepository

        @Binds
        @Singleton
        fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository

        @Binds
        @Singleton
        fun bindSeasonRepository(impl: SeasonRepositoryImpl): SeasonRepository
    }
}