package com.flea.market.ui.component

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flea.market.ui.theme.extraColors

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
public fun HeartToggleButton(
    onAddToFavourite: Boolean,
    modifier: Modifier = Modifier,
    onToggleMarkAsFavourite: (Boolean) -> Unit
) {
    IconToggleButton(
        modifier = modifier,
        checked = onAddToFavourite,
        onCheckedChange = onToggleMarkAsFavourite
    ) {
        val transition = updateTransition(targetState = onAddToFavourite, label = "transition")

        val tint by transition.animateColor(label = "iconColor") { isChecked ->
            if (isChecked) MaterialTheme.colors.error else MaterialTheme.extraColors.onScrimColor
        }

        @Suppress("MagicNumber")
        val size by transition.animateDp(
            transitionSpec = {
                if (false isTransitioningTo true) {
                    keyframes {
                        durationMillis = 250
                        30.dp at 0 using LinearOutSlowInEasing
                        35.dp at 15 using FastOutLinearInEasing
                        40.dp at 75
                        35.dp at 150
                    }
                } else {
                    spring(stiffness = Spring.StiffnessVeryLow)
                }
            },
            "size"
        ) { _ -> 30.dp }

        Icon(
            imageVector = if (onAddToFavourite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(size),
        )
    }
}
