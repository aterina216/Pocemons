package com.example.pocemons.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.pocemons.PokeApp
import com.example.pocemons.data.models.response.Pokemon

class Prefs(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("pocemons_prefs", Context.MODE_PRIVATE)

    var themeMode: Int
        get() = prefs.getInt("theme_mode", 0)
        set(value) = prefs.edit().putInt("theme_mode", value).apply()

    var addInhistory: Boolean
        get() = prefs.getBoolean("add_in_history", true)
        set(value) = prefs.edit().putBoolean("add_in_history", value).apply()

    var startActivity: String
        get() = prefs.getString("start_activity", "home") ?: "home"
        set(value) = prefs.edit().putString("start_activity", value).apply()
}