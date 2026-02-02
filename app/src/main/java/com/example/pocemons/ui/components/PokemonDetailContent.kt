package com.example.pocemons.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.pocemons.data.models.response.Pokemon
import com.example.pocemons.data.models.response.PokemonDetailResponse
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pocemons.ui.theme.PokemonRed
import com.example.pocemons.ui.theme.PokemonWhite

@Composable
fun PokemonDetailContent(
    pokemon: PokemonDetailResponse,
    modifier: Modifier = Modifier
) {
    var selectedTabInex by remember { mutableStateOf(0) }
    val tabs = listOf("About", "Stats", "Evolution")

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        PokemonHeaderSection(pokemon)

        Spacer(modifier = Modifier.height(16.dp))

        TabRow(
            selectedTabIndex = selectedTabInex,
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Transparent,
            contentColor = PokemonRed,
            indicator =  {
                tabPositions ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth()
                        .height(3.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(1f / tabs.size)
                            .fillMaxHeight()
                            .align(Alignment.BottomStart)
                            .offset(x = tabPositions[selectedTabInex].left)
                    )
                }
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabInex == index,
                    onClick = {selectedTabInex = index},
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTabInex == index) PokemonRed else PokemonWhite,
                            fontWeight = if (selectedTabInex == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when(selectedTabInex) {
            0 -> AboutTab(pokemon)
            1 -> StatsTab(pokemon)
            2 -> EvolutionTab(pokemon)
        }
    }
}

