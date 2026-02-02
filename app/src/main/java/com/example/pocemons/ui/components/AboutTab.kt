package com.example.pocemons.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pocemons.data.models.response.Pokemon
import com.example.pocemons.data.models.response.PokemonDetailResponse
import com.example.pocemons.ui.theme.PokemonBlack
import com.example.pocemons.ui.theme.PokemonRed
import com.example.pocemons.ui.theme.PokemonWhite

@Composable
fun AboutTab(pokemon: PokemonDetailResponse) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                clip = true
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = PokemonBlack.copy(alpha = 0.7f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoItem(
                    title = "Height",
                    value = "${pokemon.height / 10.0}",
                    modifier = Modifier.weight(1f)
                )

                Divider(
                    modifier = Modifier.width(1.dp)
                        .fillMaxHeight(0.5f),
                    color = PokemonRed.copy(alpha = 0.3f)
                )

                InfoItem(
                    title = "Weight",
                    value = "${pokemon.weight / 10.0} kg",
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Base Stats",
                color = PokemonWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            pokemon.stats.forEach {
                stat ->
                StatItem(
                    statName = stat.stat.name,
                    value = stat.base_stat,
                    maxValue = 255
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}