package com.example.data.ext

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
fun String.toDataTime(): LocalDateTime {
    val odt = OffsetDateTime.parse(this)
    return LocalDateTime.of(odt.year, odt.month, odt.dayOfMonth, odt.hour, odt.minute)
}

@RequiresApi(Build.VERSION_CODES.O)
fun Long.toDateTime() = LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
