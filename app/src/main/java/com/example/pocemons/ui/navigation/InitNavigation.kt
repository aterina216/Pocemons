package com.example.pocemons.ui.navigation

import HomeScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pocemons.ui.components.BottomNavigationBar
import com.example.pocemons.ui.screens.SettingsScreen
import com.example.pocemons.ui.viewmodels.PokeViewmodel

@Composable
fun InitNavigation(viewmodel: PokeViewmodel) {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    )
    { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(viewmodel)
            }

            composable("settings") {
                SettingsScreen()
            }
        }
    }
}