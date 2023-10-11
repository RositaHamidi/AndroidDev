package com.example.tryggaklassenpod.helperFunctions

import java.util.Date

fun Int.toHoursMinuteSeconds(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val remainingSeconds = this % 60

    return if (hours == 0) String.format("%02d:%02d", minutes, remainingSeconds) else String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
}

fun Long.getCreatedAtAsDate(): Date {
    return Date(this)
}