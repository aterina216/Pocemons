package com.example.pocemons.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pocemons.ui.funs.getColor.getTypeColor
import com.example.pocemons.ui.theme.PokemonWhite

@Composable
fun TypeChip(typeName: String) {

    val typeColor = getTypeColor(typeName)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(typeColor)
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(
            text = typeName.uppercase(),
            color = PokemonWhite,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}