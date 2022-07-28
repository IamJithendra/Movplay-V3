package com.example.movplayv3.domain.usecase.movie

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.movie.Movie
import com.example.movplayv3.data.repository.movie.MovieRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.GetDiscoverMoviesUseCase
import com.example.movplayv3.ui.screens.discover.movies.MovieFilterState
import com.example.movplayv3.ui.screens.discover.movies.SortInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetDiscoverMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetDiscoverMoviesUseCase {
    override fun invoke(
        sortInfo: SortInfo,
        filterState: MovieFilterState,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Movie>> {
        TODO("Not yet implemented")
    }

}