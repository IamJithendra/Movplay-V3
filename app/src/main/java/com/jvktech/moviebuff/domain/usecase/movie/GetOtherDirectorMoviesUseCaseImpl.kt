package com.jvktech.moviebuff.domain.usecase.movie

import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.CrewMember
import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.data.model.movie.Movie
import com.jvktech.moviebuff.data.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOtherDirectorMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(
        mainDirector: CrewMember,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Movie>> {
        return movieRepository.moviesOfDirector(
            directorId = mainDirector.id,
            deviceLanguage = deviceLanguage
        )
    }
}