package com.example.pocemons.ui.screens

import android.R
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pocemons.data.models.response.PokemonDetailResponse
import com.example.pocemons.ui.components.PokeballBackground
import com.example.pocemons.ui.components.PokemonDetailContent
import com.example.pocemons.ui.theme.PokemonBlack
import com.example.pocemons.ui.theme.PokemonRed
import com.example.pocemons.ui.theme.PokemonWhite
import com.example.pocemons.ui.viewmodels.PokeViewmodel
import kotlin.contracts.contract

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Composable
fun DetailScreen(viewmodel: PokeViewmodel,
                 name: String,
                 onBackClick: () -> Unit) {

    val pokemon by viewmodel.currentPokemon.collectAsState()
    val isLoading by viewmodel.isLoadingPokemon.collectAsState()

    LaunchedEffect(name) {
        viewmodel.getPokemonDetail(name)
    }

    val isCorrectPokemonLoading = remember(name, pokemon, isLoading) {
        isLoading|| pokemon?.name != name
    }

    Box(modifier = Modifier.fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    PokemonRed.copy(alpha = 0.2f),
                    PokemonBlack
                )
            )
        )) {
        PokeballBackground()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = if(pokemon?.name == name) pokemon?.name?.uppercase() ?: "Pokemon"
                            else "Loading...",
                            color = PokemonWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "Back",
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
            if (isCorrectPokemonLoading) {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .padding(paddingValues),
                     contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = PokemonRed)
                }
            }
            else if (pokemon != null && pokemon?.name == name) {
                PokemonDetailContent(
                    pokemon = pokemon!!,
                    modifier = Modifier.padding(paddingValues)
                )
            } else
            {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "Pokemon not found",
                        color = PokemonWhite,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}