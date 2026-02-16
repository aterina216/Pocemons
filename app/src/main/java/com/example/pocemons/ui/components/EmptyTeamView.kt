package com.example.pocemons.ui.components

import android.R.attr.end
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pocemons.ui.theme.PokemonBlack
import com.example.pocemons.ui.theme.PokemonWhite

@Composable
fun EmptyTeamView() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .background(MaterialTheme.colorScheme.primary))

        Box(modifier = Modifier
            .size(36.dp)
            .background(Color.Black, CircleShape),
            contentAlignment = Alignment.Center) {

            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Пусто",
                tint = PokemonWhite,
                modifier = Modifier.size(20.dp)
            )
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(PokemonWhite))
    }

    Text(
        text = "Команда пуста",
        color = PokemonWhite,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 24.dp)
    )

    Text(
        text = "Добавляйте покемонов в команду",
        color = PokemonWhite.copy(alpha = 0.7f),
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 8.dp, start = 24.dp, end = 24.dp)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 24.dp),
        colors = CardDefaults.cardColors(
            containerColor = PokemonBlack.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Максимум 6 покемонов",
                color = PokemonWhite,
                fontSize = 14.sp
            )
            LinearProgressIndicator(
                progress = 0f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .height(6.dp),
                color = MaterialTheme.colorScheme.primary,
                trackColor = PokemonWhite.copy(alpha = 0.2f)
            )
        }
    }
}