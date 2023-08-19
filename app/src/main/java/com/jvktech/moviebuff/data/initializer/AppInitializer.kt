package com.jvktech.moviebuff.data.initializer

import android.app.Application

interface AppInitializer {
    fun init(application: Application)
}