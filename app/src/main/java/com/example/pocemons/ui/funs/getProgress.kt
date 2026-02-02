package com.example.pocemons.ui.funs

import androidx.compose.ui.graphics.Color

object getProgress {

    fun getProgressColor(progress: Float): Color {
        return when {
            progress < 0.3 -> Color(0xFFFF5252) // Красный
            progress < 0.6 -> Color(0xFFFFB74D) // Оранжевый
            progress < 0.8 -> Color(0xFF4CAF50) // Зеленый
            else -> Color(0xFF2196F3) // Синий
        }
    }
}