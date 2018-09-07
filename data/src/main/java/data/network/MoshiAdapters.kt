package data.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * //TODO add description here
 */

class LocalDateTimeAdapter {
  @ToJson
  fun toJson(value: LocalDateTime): String {
    return FORMATTER.format(value)
  }

  @FromJson
  fun fromJson(value: String): LocalDateTime {
    return FORMATTER.parse(value, LocalDateTime.FROM)
  }

  companion object {
    private val FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME
  }
}

class LocalTimeAdapter {
  @ToJson
  fun toJson(value: LocalTime): String {
    return FORMATTER.format(value)
  }

  @FromJson
  fun fromJson(value: String): LocalTime {
    return FORMATTER.parse(value, LocalTime.FROM)
  }

  companion object {
    private val FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME
  }
}

class LocalDateAdapter {
  @ToJson
  fun toJson(value: LocalDate): String {
    return FORMATTER.format(value)
  }

  @FromJson
  fun fromJson(value: String): LocalDate {
    return FORMATTER.parse(value, LocalDate.FROM)
  }

  companion object {
    private val FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE
  }
}