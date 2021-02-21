package com.example.domian.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDateString(): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+00:00")
    val formattedDate: String = formatter.format(Date())
    return formatter.parse(formattedDate)
}

fun minusDate(days: Int, date: Date): Date {
    val c = Calendar.getInstance()
    c.time = date
    c.add(Calendar.DATE, days)
    return c.time
}

fun Date.dateToString(): String {
    val dateFormat: DateFormat = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss+00:00"
    )
    val c = Calendar.getInstance()
    c.time = this
    return dateFormat.format(c.time)
}

fun String.dateDayFormat(): String {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+00:00").parse(this)
    return when {
        getCurrentDateString().time - date.time < 1 -> {
            "Today"
        }
        getCurrentDateString().time - date.time in 1..2 -> {
            "Yesterday"
        }
        else ->{
            val format = SimpleDateFormat("dd MMM yyyy")
            return format.format(date)
        }
    }
}

