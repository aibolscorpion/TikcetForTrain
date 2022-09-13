package kz.almaty.divTech.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateFormatter {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(date : String): String {
        var dateTimeFormatter1 = DateTimeFormatter.ofPattern("d.M.yyyy")
        var dateTimeFormatter2 = DateTimeFormatter.ofPattern("d MMMM y")
        val formattedCheckInDate = LocalDate.parse(date, dateTimeFormatter1)

        return " ${dateTimeFormatter2.format(formattedCheckInDate)}"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDateForRequest(date : String): String {
        var dateTimeFormatter1 = DateTimeFormatter.ofPattern("d.M.yyyy")
        var dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formattedCheckInDate = LocalDate.parse(date, dateTimeFormatter1)

        return " ${dateTimeFormatter2.format(formattedCheckInDate)}"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDateAndTime(unformattedDateTime : String?): ArrayList<String> {
        val dateTimeList = ArrayList<String>()
        var dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm:ss")
        var date = DateTimeFormatter.ofPattern("d MMM")
        var time = DateTimeFormatter.ofPattern("HH:mm")
        val formattedDateTime = LocalDateTime.parse(unformattedDateTime, dateTimeFormatter1)

        dateTimeList.add(date.format(formattedDateTime))
        dateTimeList.add(time.format(formattedDateTime))
        return dateTimeList
    }
}