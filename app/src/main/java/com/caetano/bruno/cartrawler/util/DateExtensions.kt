package com.caetano.bruno.cartrawler.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class DatePatterns(val pattern: String) {
    PICK_UP_RETURN_DATE_TIME("dd MMM, h:mm a")
}

fun Date.format(pattern: String): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormat.format(this)
}