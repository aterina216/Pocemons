package com.example.pocemons.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.pocemons.ui.viewmodels.PokeViewmodel

@Composable
fun HomeScreen(viewmodel: PokeViewmodel) {

    val pokemons by viewmodel.pokemons.collectAsState()

    LazyColumn() {
        items(pokemons) { pokemon ->
            Text(pokemon?.name ?: "default")
        }
    }
}