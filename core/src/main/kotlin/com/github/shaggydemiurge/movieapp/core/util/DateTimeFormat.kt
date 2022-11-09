package com.github.shaggydemiurge.movieapp.core.util

import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

object DateTimeFormat {

    val SLASH_DMY = DateTimeFormatterBuilder()
        .appendValue(ChronoField.DAY_OF_MONTH, 2)
        .appendLiteral("/")
        .appendValue(ChronoField.MONTH_OF_YEAR, 2)
        .appendLiteral("/")
        .appendValue(ChronoField.YEAR)
        .toFormatter()
}
