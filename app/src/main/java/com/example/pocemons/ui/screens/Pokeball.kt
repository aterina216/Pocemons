package com.example.pocemons.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pocemons.ui.components.EmptyTeamView
import com.example.pocemons.ui.components.PokeballBackground
import com.example.pocemons.ui.components.TeamPokemonList
import com.example.pocemons.ui.theme.PokemonBlack
import com.example.pocemons.ui.theme.PokemonWhite
import com.example.pocemons.ui.viewmodels.PokeViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pokeball(
    viewmodel: PokeViewmodel,
    navController: NavController,
    onBackClick: () -> Unit
) {
    val teamPokemons by viewmodel.teamPokemons.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
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
                            text = "Моя команда",
                            color = PokemonWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(onBackClick) {
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
        ) {
            paddingValues ->
            if(teamPokemons.isEmpty()) {
                EmptyTeamView()
            }
            else {
                TeamPokemonList(
                    pokemons = teamPokemons,
                    navController = navController,
                    onRemoveFromTeam = {
                        pokemon ->
                        viewmodel.togglePokemonInTeam(pokemon.id)
                    },
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}