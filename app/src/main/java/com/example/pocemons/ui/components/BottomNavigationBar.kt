package com.example.pocemons.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pocemons.R
import com.example.pocemons.ui.theme.PokemonRed

@Composable
fun BottomNavigationBar(navController: NavController) {

    val navBackStackEntry by  navController.currentBackStackEntryAsState()
    var currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        PokemonNavItem("home",
            "Главная",
            R.drawable.baseline_home_24,
            R.drawable.outline_home_24),
        PokemonNavItem("settings",
            "Настройки",
            R.drawable.baseline_settings_24,
            R.drawable.outline_settings_24
        )
    )

    Box(modifier = Modifier.fillMaxWidth()
        .height(80.dp)
        .shadow(
            elevation = 16.dp,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            clip = true
        )
        .background(
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ).clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            PokemonRed.copy(alpha = 0.1f),
                            Color.Transparent,
                            PokemonRed.copy(alpha = 0.1f)
                        )
                    ),
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach {
                item ->
                PokemonBottomBarItem(
                    item = item,
                    isSelected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )

            }
        }
    }
}