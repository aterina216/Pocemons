package com.example.pocemons.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pocemons.data.models.entity.PokemonEntity
import androidx.compose.foundation.lazy.items

@Composable
fun PokemonListView(
    pokemons: List<PokemonEntity>,
    lazyListState: LazyListState,
    onPokemonClick: (PokemonEntity) -> Unit
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
                onClick = {onPokemonClick(pokemon)}
            )
        }
    }
}