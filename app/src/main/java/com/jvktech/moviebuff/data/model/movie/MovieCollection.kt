package com.jvktech.moviebuff.data.model.movie

import com.jvktech.moviebuff.data.model.Part

data class MovieCollection(
    val name: String,
    val parts: List<Part>
)
