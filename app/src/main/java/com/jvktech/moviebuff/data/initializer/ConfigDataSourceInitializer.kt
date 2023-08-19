package com.jvktech.moviebuff.data.initializer

import android.app.Application
import com.jvktech.moviebuff.data.paging.ConfigDataSource
import javax.inject.Inject

class ConfigDataSourceInitializer @Inject constructor(
    private val configDataSource: ConfigDataSource
    ) : AppInitializer {
    override fun init(application: Application) {
        configDataSource.init()
    }
}