package com.jvktech.moviebuff.domain.usecase

import androidx.paging.PagingData
import com.jvktech.moviebuff.data.model.MediaType
import com.jvktech.moviebuff.data.model.Review
import com.jvktech.moviebuff.data.repository.movie.MovieRepository
import com.jvktech.moviebuff.data.repository.tvshow.TvShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GetMediaTypeReviewsUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvShowRepository: TvShowRepository
) {
    operator fun invoke(mediaId: Int, type: MediaType): Flow<PagingData<Review>> {
        return when (type) {
            MediaType.Movie -> movieRepository.movieReviews(mediaId)
            MediaType.Tv -> tvShowRepository.tvShowReviews(mediaId)
            else -> emptyFlow()
        }
    }
}