package com.example.pocemons.ui.funs

import java.sql.Date
import java.sql.Struct
import java.text.SimpleDateFormat
import java.util.Locale

object FormatTime {

    fun formatTime(timestamp: Long): String {
        if (timestamp == 0L) return "Только что"

        val date = Date(timestamp)
        val now = System.currentTimeMillis()
        val diff = now - timestamp

        return when {
            diff < 60000 -> "Только что"
            diff < 3600000 -> "${diff / 60000} мин. назад"
            diff < 86400000 -> "${diff / 3600000} ч. назад"
            else -> {
                val sdf = SimpleDateFormat("dd MMM, HH:mm", Locale.getDefault())
                sdf.format(date)
            }
        }
    }
}