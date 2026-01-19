package com.example.pocemons.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo
import coil.compose.AsyncImage
import com.example.pocemons.data.models.entity.PokemonEntity
import com.example.pocemons.ui.theme.PokemonRed
import com.example.pocemons.ui.theme.PokemonWhite

@Composable
fun PocemonCard(
    pokemon: PokemonEntity,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
    ) {

    Card (
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
            pressedElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = onClick
    )
    {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            PokemonRed.copy(alpha = 0.1f),
                            PokemonRed.copy(alpha = 0.05f)
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                ))

            Row(
               modifier = Modifier.fillMaxSize()
                   .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = pokemon.getPokemonNumber(),
                        color = PokemonRed,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )

                    Text(
                        text = pokemon.capitalizedName(),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 20.sp,
                        maxLines = 1
                    )

                    Text(
                        "Подробнее ->",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 8.dp)
                        )
                }
                Box(modifier = Modifier.size(96.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        color = PokemonWhite,
                        shape = RoundedCornerShape(12.dp)
                    )) {
                    AsyncImage(
                        model = pokemon.imageUrl,
                        contentDescription = pokemon.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Box(modifier = Modifier
                .size(40.dp)
                .background(
                    color = PokemonWhite,
                    shape = RoundedCornerShape(20.dp)
                )
                .align(Alignment.TopEnd)
                .padding(8.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()
                    .background(
                        color = PokemonRed,
                        shape = RoundedCornerShape(16.dp)
                    ))
            }
        }
    }
}