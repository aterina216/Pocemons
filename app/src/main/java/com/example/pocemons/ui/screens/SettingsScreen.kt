package com.example.pocemons.ui.screens

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pocemons.ui.components.PokeballBackground
import com.example.pocemons.ui.components.ThemeOption
import com.example.pocemons.ui.theme.PokemonBlack
import com.example.pocemons.ui.theme.PokemonWhite
import com.example.pocemons.ui.viewmodels.PokeViewmodel
import com.example.pocemons.utils.ThemeMode
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.focusModifier
import com.example.pocemons.ui.components.StartScreenOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewmodel: PokeViewmodel,
    onBackClick: () -> Unit
) {

    val themeMode by viewmodel.themeMode.collectAsState()
    val addInHistory by viewmodel.addInHistory.collectAsState()
    val startActivity by viewmodel.startActivity.collectAsState()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                        PokemonBlack
                    )
                )
            )
    ) {
        PokeballBackground()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Настройки",
                            color = PokemonWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "Назад",
                                tint = PokemonWhite
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        scrolledContainerColor = PokemonBlack.copy(alpha = 0.9f)
                    )
                )
            },
            containerColor = Color.Transparent
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = "Внешний вид",
                        color = PokemonWhite.copy(alpha = 0.9f),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                item {
                    Card(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .shadow(
                                    elevation = 4.dp,
                                    shape = RoundedCornerShape(16.dp),
                                    clip = true
                                ),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = PokemonBlack.copy(alpha = 0.7f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Тема оформления",
                                color = PokemonWhite,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            ThemeOption(
                                icon = com.example.pocemons.R.drawable.sharp_settings_24,
                                title = "Системная",
                                description = "Автоматически",
                                selected = themeMode == ThemeMode.SYSTEM,
                                onClick = { viewmodel.setThemeMode(ThemeMode.SYSTEM) }
                            )

                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 8.dp),
                                color = PokemonWhite.copy(alpha = 0.2f),
                                thickness = 1.dp
                            )
                            ThemeOption(
                                icon = com.example.pocemons.R.drawable.outline_sunny_24,
                                title = "Светлая",
                                description = "Всегда светлая",
                                selected = themeMode == ThemeMode.LIGHT,
                                onClick = { viewmodel.setThemeMode(ThemeMode.LIGHT) }
                            )
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 8.dp),
                                color = PokemonWhite.copy(alpha = 0.2f),
                                thickness = 1.dp
                            )
                            ThemeOption(
                                icon = com.example.pocemons.R.drawable.outline_dark_mode_24,
                                title = "Тёмная",
                                description = "Всегда темная",
                                selected = themeMode == ThemeMode.DARK,
                                onClick = { viewmodel.setThemeMode(ThemeMode.DARK) }
                            )
                        }
                    }
                }

                item {
                    Text(
                        text = "История",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 4.dp,
                                shape = RoundedCornerShape(16.dp),
                                clip = true
                            ),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Сохранять историю просмотров",
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.weight(1f)
                                )

                                Switch(
                                    checked = addInHistory,
                                    onCheckedChange = { viewmodel.setAddInHistory(it) },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                                        checkedTrackColor = MaterialTheme.colorScheme.primary.copy(
                                            alpha = 0.5f
                                        ),
                                        uncheckedThumbColor = MaterialTheme.colorScheme.onSurface.copy(
                                            alpha = 0.6f
                                        ),
                                        uncheckedTrackColor = MaterialTheme.colorScheme.onSurface.copy(
                                            alpha = 0.2f
                                        )
                                    )
                                )
                            }
                        }
                    }
                }

                item {
                    Text(
                        text = "Кэш и данные",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 4.dp,
                                shape = RoundedCornerShape(16.dp),
                                clip = true
                            )
                            .clickable {viewmodel.clearAll()},
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            // Строка "Сбросить все данные"
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Delete,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(
                                    text = "Сбросить все данные",
                                    color = MaterialTheme.colorScheme.error,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }

                item {
                    Text(
                        text = "Стартовый экран",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth()
                            .shadow(
                                elevation = 4.dp,
                                shape = RoundedCornerShape(16.dp),
                                clip = true
                            ),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                        )
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()
                            .padding(16.dp)) {

                            StartScreenOption(
                                title = "Главная",
                                description = "Список покемонов",
                                selected = startActivity == "home",
                                onClick = { viewmodel.setStartActivity("home") }
                            )

                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 8.dp),
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                                thickness = 1.dp
                            )

                            StartScreenOption(
                                title = "Ваша команда",
                                description = "Команда покемонов",
                                selected = startActivity == "pokeball",
                                onClick = { viewmodel.setStartActivity("pokeball") }
                            )

                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 8.dp),
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                                thickness = 1.dp
                            )

                            StartScreenOption(
                                title = "История",
                                description = "История просмотров",
                                selected = startActivity == "history",
                                onClick = { viewmodel.setStartActivity("history") }
                            )

                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 8.dp),
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                                thickness = 1.dp
                            )

                            StartScreenOption(
                                title = "Настройки",
                                description = "Экран настроек",
                                selected = startActivity == "settings",
                                onClick = { viewmodel.setStartActivity("settings") }
                            )

                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 8.dp),
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                                thickness = 1.dp
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.padding(bottom = 16.dp))
                }
            }
        }
    }
}