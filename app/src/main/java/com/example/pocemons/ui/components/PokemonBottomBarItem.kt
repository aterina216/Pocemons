package com.example.pocemons.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo
import com.example.pocemons.ui.theme.PokemonGray
import com.example.pocemons.ui.theme.PokemonRed
import dagger.Component

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PokemonBottomBarItem(
    item: PokemonNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 8.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            if(isSelected) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = PokemonRed.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(20.dp)
                        )
                )
            }

            AnimatedContent(
                targetState = isSelected,
                transitionSpec = {
                    scaleIn(animationSpec = tween(200)) + fadeIn() togetherWith
                            scaleOut(animationSpec = tween(200)) + fadeOut()
                }
            ) {
                selected ->
                Icon(
                    painter = painterResource(
                        id = if(selected) item.activeIcon
                        else item.icon
                    ),
                    contentDescription = item.title,
                    tint = if (selected) PokemonRed else PokemonGray,
                    modifier = Modifier.size(24.dp)
                )
            }
            if(isSelected) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(8.dp)
                        .background(
                            color = PokemonRed,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))

        AnimatedContent(
            targetState = isSelected,
            transitionSpec = {
                fadeIn(animationSpec = tween(150)) +
                        slideInVertically(animationSpec = tween(150)) { -it } togetherWith
                        fadeOut(animationSpec = tween(150)) +
                        slideOutVertically(animationSpec = tween(150)) { it }
            }
        ) {
                selected ->
            Text(
                text = item.title,
                fontSize = if (selected) 11.sp else 10.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                color = if (selected) PokemonRed else PokemonGray,
                maxLines = 1
            )
        }
    }
}

