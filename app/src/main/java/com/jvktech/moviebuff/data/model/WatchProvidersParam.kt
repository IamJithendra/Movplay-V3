package com.jvktech.moviebuff.data.model

data class WatchProvidersParam(private val watchProviders: List<ProviderSource>) {
    override fun toString(): String {
        return watchProviders.distinct().map { provider -> provider.providerId }
            .joinToString(separator = "|")
    }
}