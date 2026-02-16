package com.example.pocemons.ui.navigation

import HomeScreen
import android.R.attr.type
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pocemons.ui.components.BottomNavigationBar
import com.example.pocemons.ui.screens.DetailScreen
import com.example.pocemons.ui.screens.HistoryScreen
import com.example.pocemons.ui.screens.Pokeball
import com.example.pocemons.ui.screens.SettingsScreen
import com.example.pocemons.ui.viewmodels.PokeViewmodel
import kotlinx.coroutines.flow.compose

@Composable
fun InitNavigation(viewmodel: PokeViewmodel) {

    val navController = rememberNavController()
    val startActivity = remember { viewmodel.startActivity.value }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    )
    { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = startActivity,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(viewmodel, navController = navController)
            }

            composable("settings") {
                SettingsScreen(
                    viewmodel = viewmodel,
                    onBackClick = {navController.popBackStack()}
                )
            }

            composable("details/{name}",
                arguments = listOf(navArgument("name") {
                    type = NavType.StringType
                })) {
                backStackEntry ->
                val name = backStackEntry.arguments?.getString("name")
                if(name != null) {
                    DetailScreen(viewmodel, name, onBackClick = {navController.popBackStack()})
                }
            }

            composable("pokeball") {
                Pokeball(
                    viewmodel = viewmodel,
                    navController = navController,
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable("history") {
                HistoryScreen(
                    viewmodel = viewmodel,
                    navController = navController,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}