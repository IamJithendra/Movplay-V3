package com.jvktech.moviebuff.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.Duration.Companion.minutes

fun Int.formattedRuntime(): String? {
    return minutes.toComponents { hours, minutes, _, _ ->
        val hoursString = if (hours > 0) "${hours}hr" else null
        val minutesString = if (minutes > 0) "${minutes}min" else null

        listOfNotNull(hoursString, minutesString).run {
            if (isEmpty()) null else joinToString(separator = " ")
        }
    }
}

fun Float.singleDecimalPlaceFormatted(): String {
    return String.format("%.1f", this)
}

fun Long.formattedMoney(): String {
    return NumberFormat.getCurrencyInstance(Locale.US).apply {
        maximumFractionDigits = 0
    }.format(this).replace(",", " ")
}


fun convertDate(inputDate: Date): String {
    val outputFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH)
    return outputFormat.format(inputDate)
}
