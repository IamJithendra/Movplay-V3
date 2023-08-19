package com.jvktech.moviebuff.data.model

data class DeviceLanguage(
    val region: String,
    val languageCode: String
) {
    companion object {
        val default: DeviceLanguage = DeviceLanguage(
            region = "US",
            languageCode = "en-US"
        )
    }
}