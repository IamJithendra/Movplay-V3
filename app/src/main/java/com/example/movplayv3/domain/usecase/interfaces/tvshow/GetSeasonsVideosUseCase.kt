package com.example.movplayv3.domain.usecase.interfaces.tvshow

import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.Video
import com.example.movplayv3.data.remote.api.ApiResponse

interface GetSeasonsVideosUseCase {
    suspend operator fun invoke(
        tvShowId: Int,
        seasonNumber: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<List<Video>>
}