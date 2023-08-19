package com.jvktech.moviebuff.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenresResponse(
    val genres: List<Genre>
)