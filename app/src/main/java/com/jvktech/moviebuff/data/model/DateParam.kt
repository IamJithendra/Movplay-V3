package com.jvktech.moviebuff.data.model

import com.jvktech.moviebuff.utils.formatted
import java.util.*

data class DateParam(private val date: Date) {
    override fun toString(): String {
        return date.formatted("yyyy-MM-dd")
    }
}
