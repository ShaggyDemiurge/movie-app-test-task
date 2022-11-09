package com.github.shaggydemiurge.movieapp.data.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class DateAdapter {

    @ToJson
    fun toJson(date: LocalDate): String = DateTimeFormatter.ISO_LOCAL_DATE.format(date)

    @FromJson
    fun fromJson(json: String): LocalDate? = try {
        LocalDate.parse(json.trim())
    } catch (ex: DateTimeParseException) {
        null
    }
}
