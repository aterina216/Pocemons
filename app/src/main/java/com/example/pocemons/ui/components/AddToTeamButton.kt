package com.example.pocemons.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.example.pocemons.R
import com.example.pocemons.ui.theme.PokemonRed
import com.example.pocemons.ui.theme.PokemonRedDark
import com.example.pocemons.ui.theme.PokemonWhite
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AddToTeamButton(
    isInTeam: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.9f else 1f,
        label = "buttonScale"
    )
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(PokemonRed, PokemonRedDark),
                    radius = 300f
                )
            )
            .scale(scale)
            .clickable {
                pressed = true
                onClick()
                scope.launch {
                    delay(1000)
                    pressed = false
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(PokemonWhite)
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(PokemonRed),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(
                    id = if(isInTeam) R.drawable.ic_check else R.drawable.ic_add
                ),
                contentDescription = if (isInTeam) "Удалить из команды" else "Добавить в команду",
                tint = PokemonWhite,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}