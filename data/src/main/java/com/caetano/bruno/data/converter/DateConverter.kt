package com.caetano.bruno.data.converter

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal fun convertToDate(date: String): Date {
    return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(date)
}