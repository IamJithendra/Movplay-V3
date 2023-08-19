package com.jvktech.moviebuff.data.repository.config

import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.data.model.Genre
import com.jvktech.moviebuff.data.model.ProviderSource
import com.jvktech.moviebuff.utils.ImageUrlParser
import kotlinx.coroutines.flow.Flow

interface ConfigRepository {
    fun isInitialized(): Flow<Boolean>

    fun updateLocale()

    fun getSpeechToTextAvailable(): Flow<Boolean>

    fun getCameraAvailable(): Flow<Boolean>

    fun getDeviceLanguage(): Flow<DeviceLanguage>

    fun getImageUrlParser(): Flow<ImageUrlParser?>

    fun getMoviesGenres(): Flow<List<Genre>>

    fun getTvShowGenres(): Flow<List<Genre>>

    fun getAllMoviesWatchProviders(): Flow<List<ProviderSource>>

    fun getAllTvShowWatchProviders(): Flow<List<ProviderSource>>
}