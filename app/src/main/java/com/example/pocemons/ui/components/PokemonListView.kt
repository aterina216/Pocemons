package com.example.pocemons.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pocemons.data.models.entity.PokemonEntity
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.pocemons.ui.theme.PokemonRed
import com.example.pocemons.ui.viewmodels.PokeViewmodel

@Composable
fun PokemonListView(
    pokemons: List<PokemonEntity>,
    isLoadingMore: Boolean,
    hasMorePokemons: Boolean,
    lazyListState: LazyListState,
    onPokemonClick: (PokemonEntity) -> Unit,
    onLoadMore: () -> Unit,
    navController: NavController
) {

    LazyColumn(
        state = lazyListState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(pokemons) {
            pokemon ->
            PocemonCard(
                pokemon,
                onClick = {onPokemonClick(pokemon)},
                navController = navController
            )
        }

        if(isLoadingMore) {
            item {
                Box(modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = PokemonRed)
                }
            }
        }

        if(hasMorePokemons && !isLoadingMore) {
            item {
                Button(
                    onClick = onLoadMore,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PokemonRed
                    )
                ) {
                    Text("Загрузить еще")
                }
            }
        }
    }
}