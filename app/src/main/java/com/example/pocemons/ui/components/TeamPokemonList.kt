package com.example.pocemons.ui.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pocemons.data.models.entity.PokemonEntity
import com.example.pocemons.ui.theme.PokemonBlack
import com.example.pocemons.ui.theme.PokemonRed
import com.example.pocemons.ui.theme.PokemonWhite

@Composable
fun TeamPokemonList(
    pokemons: List<PokemonEntity>,
    navController: NavController,
    onRemoveFromTeam: (PokemonEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = PokemonBlack.copy(alpha = 0.5f)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Ваша команда",
                            color = PokemonWhite,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "${pokemons}/6 покемонов",
                            color = PokemonRed,
                            fontSize = 14.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(PokemonRed, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = pokemons.size.toString(),
                            color = PokemonWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        items(pokemons, key = { it.id}) {
            pokemon ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItem(tween(durationMillis = 300)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = PokemonBlack.copy(alpha = 0.7f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box {
                    PocemonCard(
                        pokemon,
                        onClick = {
                            navController.navigate("details/${pokemon.name}")
                        },
                        navController = navController
                    )

                    IconButton(
                        onClick = { onRemoveFromTeam(pokemon)},
                        modifier = Modifier
                            .size(36.dp)
                            .align(Alignment.TopEnd)
                            .offset(x = (-8).dp, y = 8.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = PokemonRed,
                            contentColor = PokemonWhite
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Удалить из команды",
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .offset(x = 12.dp, y = (-8).dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(PokemonRed, PokemonBlack)
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                    {
                        Text(
                            text = "В команде",
                            color = PokemonWhite,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}