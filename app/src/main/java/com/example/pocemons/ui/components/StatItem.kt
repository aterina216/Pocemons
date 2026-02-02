package com.example.pocemons.ui.components

import android.R
import android.app.BackgroundServiceStartNotAllowedException
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pocemons.ui.funs.formatName.formatStatName
import com.example.pocemons.ui.funs.getProgress.getProgressColor
import com.example.pocemons.ui.theme.PokemonWhite

@Composable
fun StatItem(
    statName: String,
    value: Int,
    maxValue: Int
) {

    val progress = value.toFloat() / maxValue

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatStatName(statName),
                color = PokemonWhite,
                fontSize = 14.sp
            )

            Text(
                text = value.toString(),
                color = PokemonWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth().height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = getProgressColor(progress),
            trackColor = PokemonWhite.copy(alpha = 0.1f)
        )
    }
}