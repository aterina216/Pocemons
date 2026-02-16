package com.example.pocemons.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.pocemons.ui.viewmodels.PokeViewmodel
import com.example.pocemons.utils.ThemeMode

private val DarkColorScheme = darkColorScheme(
    primary = PokemonRedd,
    secondary = PokemonBlue,
    tertiary = PokemonYellow,
    background = PokemonBlack,
    surface = PokemonGrayDark,
    onPrimary = PokemonWhite,
    onSecondary = PokemonWhite,
    onBackground = PokemonWhite,
    onSurface = PokemonGray
)

private val LightColorScheme = lightColorScheme(
    primary = PokemonRedd,
    secondary = PokemonBlue,
    tertiary = PokemonYellow,
    background = PokemonWhite,
    surface = PokemonWhite,
    onPrimary = PokemonWhite,
    onSecondary = PokemonWhite,
    onBackground = PokemonBlack,
    onSurface = PokemonGrayDark
)

@Composable
fun PocemonsTheme(
    viewmodel: PokeViewmodel,
    content: @Composable () -> Unit
) {
    val theme by viewmodel.themeMode.collectAsState()
    val systemIsDark = isSystemInDarkTheme()

    val darkTheme = when(theme) {
        ThemeMode.SYSTEM -> systemIsDark
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }

    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if(!view.isInEditMode) {
        val window = (view.context as Activity).window
        window.statusBarColor = colorScheme.primary.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}