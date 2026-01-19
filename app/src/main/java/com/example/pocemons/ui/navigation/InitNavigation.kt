package com.example.pocemons.ui.navigation

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pocemons.ui.viewmodels.PokeViewmodel

@Composable
fun InitNavigation(viewmodel: PokeViewmodel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(viewmodel)
        }
    }
}