package com.example.movplayv3.data.repository.movie

import androidx.paging.PagingData
import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.movie.Movie
import com.example.movplayv3.data.model.movie.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun discoverMovies(
        deviceLanguage: DeviceLanguage,
        sortType: SortType = SortType.Popularity,
        sortOrder: SortOrder = SortOrder.Desc,
        genresParam: GenresParam = GenresParam(genres = emptyList()),
        watchProvidersParam: WatchProvidersParam = WatchProvidersParam(watchProviders = emptyList()),
        voteRange: ClosedFloatingPointRange<Float> = 0f..10f,
        onlyWithPosters: Boolean = false,
        onlyWithScore: Boolean = false,
        onlyWithOverview: Boolean = false,
        releaseDateRange: DateRange = DateRange()
    ): Flow<PagingData<Movie>>

    fun popularMovies(
        deviceLanguage: DeviceLanguage = DeviceLanguage.default
    ): Flow<PagingData<MovieEntity>>
}