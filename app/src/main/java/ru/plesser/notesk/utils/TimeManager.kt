package ru.plesser.notesk.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeManager {
    fun getCurrentTime(): String{
        val formatter = SimpleDateFormat("HH:mm:ss - yyyy.MM.dd", Locale.getDefault())
        return formatter.format(Calendar.getInstance().time)

    }

    fun getTime(date: Date): String{
        val formatter = SimpleDateFormat("HH:mm:ss - yyyy.MM.dd", Locale.getDefault())
        return formatter.format(date)

    }
}