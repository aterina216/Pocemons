package com.example.pocemons.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pocemons.data.models.response.Stat
import com.example.pocemons.ui.funs.formatName.formatStatName
import com.example.pocemons.ui.funs.formatName.getStatAbbreviation
import com.example.pocemons.ui.funs.getProgress
import com.example.pocemons.ui.funs.getProgress.getProgressColor
import com.example.pocemons.ui.theme.PokemonRed
import com.example.pocemons.ui.theme.PokemonWhite

@Composable
fun EnhancedStatItem(
    stat: Stat,
    modifier: Modifier = Modifier
) {

    val statName = formatStatName(stat.stat.name)
    val value = stat.base_stat
    val progress = value/255f

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier.size(40.dp)
                .background(
                    color = PokemonRed.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = getStatAbbreviation(stat.stat.name),
                color = PokemonRed,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = statName,
                color = PokemonWhite,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = getProgressColor(progress),
                trackColor = PokemonWhite.copy(alpha = 0.1f)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = value.toString(),
            color = PokemonWhite,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(40.dp),
            textAlign = TextAlign.End
        )
    }
}