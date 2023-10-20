package com.flea.market.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode.Reverse
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flea.market.ui.component.ButtonState.Initial
import com.flea.market.ui.component.ButtonState.Loading
import com.flea.market.ui.component.ButtonState.Result
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.theme.extraColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AddToCartButton(
    buttonState: ButtonState,
    initialContent: @Composable (RowScope) -> Unit,
    resultContent: @Composable (RowScope) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding
) {
    val image = ImageBitmap.imageResource(id = R.drawable.ic_cart)
    val painter = remember { BitmapPainter(image = image) }

    val contentColor = MaterialTheme.colors.secondary

    val animationCartProgress = remember { Animatable(0f) }
    val animationItemProgress = remember { Animatable(0f) }
    val animationWobbleCartProgress = remember { Animatable(0f) }

    val animationLoadingDotProgress = remember { Animatable(0f) }

    val localDensity = LocalDensity.current
    var initialHeight by remember { mutableStateOf(0.dp) }
    var initialWidth by remember { mutableStateOf(0.dp) }

    Button(enabled = enabled,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        onClick = { if (buttonState == Initial) onClick() },
        modifier = modifier
            .onGloballyPositioned {
                if (initialHeight.value == 0f) {
                    initialHeight = with(localDensity) { it.size.height.toDp() }
                    initialWidth = with(localDensity) { it.size.width.toDp() }
                }
            }
            .drawWithContent {
                drawContent()

                drawItemsAsPerState(
                    buttonState,
                    contentColor,
                    animationLoadingDotProgress,
                    animationItemProgress,
                    animationCartProgress,
                    animationWobbleCartProgress,
                    painter
                )
            }) {
        ButtonContent(
            buttonState,
            animationCartProgress,
            initialContent,
            resultContent,
            initialHeight,
            contentPadding,
            initialWidth
        )
    }

    AnimateAsPerButtonState(
        buttonState,
        animationCartProgress,
        animationWobbleCartProgress,
        animationItemProgress,
        animationLoadingDotProgress
    )
}

private const val LOADING_DOT_ANIMATION_MILLIS = 800

@Composable
private fun AnimateAsPerButtonState(
    buttonState: ButtonState,
    animationCartProgress: Animatable<Float, AnimationVector1D>,
    animationWobbleCartProgress: Animatable<Float, AnimationVector1D>,
    animationItemProgress: Animatable<Float, AnimationVector1D>,
    animationLoadingDotProgress: Animatable<Float, AnimationVector1D>
) {
    LaunchedEffect(buttonState) {
        when (buttonState) {
            Result -> {
                launch {
                    animationCartProgress.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = 900, easing = FastOutSlowInEasing)
                    )

                    animationWobbleCartProgress.animateTo(
                        targetValue = 1f, animationSpec = repeatable(
                            iterations = 3,
                            animation = tween(durationMillis = 200),
                            repeatMode = Reverse
                        )
                    )
                    animationWobbleCartProgress.snapTo(0f)

                    animationCartProgress.animateTo(
                        targetValue = 2f, animationSpec = tween(
                            durationMillis = 600, easing = FastOutLinearInEasing, delayMillis = 100
                        )
                    )
                }

                launch {
                    animationItemProgress.animateTo(
                        targetValue = 1f, animationSpec = tween(
                            durationMillis = LOADING_DOT_ANIMATION_MILLIS,
                            easing = FastOutSlowInEasing
                        )
                    )

                    animationItemProgress.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
                    )
                }

            }

            Initial -> {
                animationCartProgress.snapTo(0f)
                animationWobbleCartProgress.snapTo(0f)
                animationItemProgress.snapTo(0f)
                animationLoadingDotProgress.snapTo(0f)
            }

            Loading -> {
                launch {
                    animationLoadingDotProgress.animateTo(
                        targetValue = 1f, animationSpec = infiniteRepeatable(
                            animation = tween(LOADING_DOT_ANIMATION_MILLIS), repeatMode = Reverse
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.ButtonContent(
    buttonState: ButtonState,
    animationCartProgress: Animatable<Float, AnimationVector1D>,
    initialContent: @Composable (RowScope) -> Unit,
    resultContent: @Composable (RowScope) -> Unit,
    initialHeight: Dp,
    contentPadding: PaddingValues,
    initialWidth: Dp
) {
    if (buttonState == Initial && animationCartProgress.value == 0f) {
        initialContent(this)
    } else if (buttonState == Result && animationCartProgress.value == 2f) {
        resultContent(this)
    } else {
        Spacer(
            modifier = Modifier
                .height(initialHeight - contentPadding.calculateBottomPadding() - contentPadding.calculateTopPadding())
                .width(
                    initialWidth - contentPadding.calculateStartPadding(
                        LocalLayoutDirection.current
                    ) - contentPadding.calculateEndPadding(
                        LocalLayoutDirection.current
                    )
                )
        )
    }
}

@Suppress("LongParameterList")
private fun ContentDrawScope.drawItemsAsPerState(
    buttonState: ButtonState,
    contentColor: Color,
    animationLoadingDotProgress: Animatable<Float, AnimationVector1D>,
    animationItemProgress: Animatable<Float, AnimationVector1D>,
    animationCartProgress: Animatable<Float, AnimationVector1D>,
    animationWobbleCartProgress: Animatable<Float, AnimationVector1D>,
    painter: BitmapPainter
) {
    val halfWidth = this.size.width / 2
    val halfHeight = this.size.height / 2

    val cartIconSize = 26.dp.toPx()

    val dotOffset = 2.dp.toPx()
    val dotRadius = 2.dp.toPx()
    val dotDiameter = dotRadius * 2
    val dotSpacing = 2.dp.toPx()

    val loadingRadius = 4.dp.toPx()
    val loadingDiameter = loadingRadius * 2
    val loadingSpacing = 8.dp.toPx()

    when (buttonState) {
        Loading -> drawLoading(
            contentColor = contentColor,
            loadingRadius = loadingRadius,
            animationLoadingDotProgress = animationLoadingDotProgress,
            halfWidth = halfWidth,
            loadingDiameter = loadingDiameter,
            loadingSpacing = loadingSpacing,
            halfHeight = halfHeight
        )

        Result -> {
            if (animationItemProgress.value != 0f) {
                drawItems(
                    animationItemProgress = animationItemProgress,
                    contentColor = contentColor,
                    dotRadius = dotRadius,
                    halfWidth = halfWidth,
                    dotOffset = dotOffset,
                    dotDiameter = dotDiameter,
                    dotSpacing = dotSpacing,
                    halfHeight = halfHeight
                )
            }


            if (animationCartProgress.value != 0f && animationCartProgress.value != 2f) {
                drawCart(
                    animationCartProgress = animationCartProgress,
                    halfWidth = halfWidth,
                    cartIconSize = cartIconSize,
                    animationWobbleCartProgress = animationWobbleCartProgress,
                    painter = painter,
                    contentColor = contentColor
                )
            }
        }

        Initial -> {}
    }
}

sealed class ButtonState {
    object Initial : ButtonState()
    object Loading : ButtonState()
    object Result : ButtonState()
}

@Suppress("LongParameterList")
private fun ContentDrawScope.drawLoading(
    contentColor: Color,
    loadingRadius: Float,
    animationLoadingDotProgress: Animatable<Float, AnimationVector1D>,
    halfWidth: Float,
    loadingDiameter: Float,
    loadingSpacing: Float,
    halfHeight: Float
) {
    drawCircle(
        color = contentColor,
        radius = loadingRadius * animationLoadingDotProgress.value,
        center = Offset(
            halfWidth - loadingDiameter - loadingSpacing, halfHeight
        )
    )

    drawCircle(
        color = contentColor,
        radius = loadingRadius * animationLoadingDotProgress.value,
        center = Offset(halfWidth, halfHeight)
    )

    drawCircle(
        color = contentColor,
        radius = loadingRadius * animationLoadingDotProgress.value,
        center = Offset(
            halfWidth + loadingDiameter + loadingSpacing, halfHeight
        )
    )
}

@Suppress("LongParameterList")
private fun DrawScope.drawCart(
    animationCartProgress: Animatable<Float, AnimationVector1D>,
    halfWidth: Float,
    cartIconSize: Float,
    animationWobbleCartProgress: Animatable<Float, AnimationVector1D>,
    painter: BitmapPainter,
    contentColor: Color
) {
    translate(
        left = (halfWidth - cartIconSize / 2) * animationCartProgress.value,
        top = (this.size.height - cartIconSize) / 2
    ) {
        rotate(
            degrees = 350f + 10 * animationWobbleCartProgress.value,
            pivot = Offset(cartIconSize / 2, cartIconSize / 2)
        ) {
            with(painter) {
                draw(
                    size = Size(cartIconSize, cartIconSize),
                    colorFilter = ColorFilter.tint(contentColor)
                )
            }
        }
    }
}

@Suppress("LongParameterList")
private fun DrawScope.drawItems(
    animationItemProgress: Animatable<Float, AnimationVector1D>,
    contentColor: Color,
    dotRadius: Float,
    halfWidth: Float,
    dotOffset: Float,
    dotDiameter: Float,
    dotSpacing: Float,
    halfHeight: Float
) {
    drawCircle(
        color = contentColor, radius = dotRadius, center = Offset(
            halfWidth + dotOffset - dotDiameter - dotSpacing,
            halfHeight - animationItemProgress.value * 28.dp.value
        )
    )

    drawCircle(
        color = contentColor, radius = dotRadius, center = Offset(
            halfWidth + dotOffset, halfHeight - animationItemProgress.value * 36.dp.value
        )
    )

    drawCircle(
        color = contentColor, radius = dotRadius, center = Offset(
            halfWidth + dotOffset + dotDiameter + dotSpacing,
            halfHeight - animationItemProgress.value * 42.dp.value
        )
    )
}


@FleaMarketPreviews// Start interactive mode to see the animations
@Composable
fun AddToCartButtonInitialPreview() {
    FleaMarketThemePreview {
        var buttonState: ButtonState by remember { mutableStateOf(Initial) }
        val coroutineScope = rememberCoroutineScope()

        AddToCartButton(buttonState = buttonState, initialContent = {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "ADD TO CART")
        }, resultContent = {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = MaterialTheme.extraColors.successColor
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "ADDED")
        }, onClick = {
            buttonState = Loading
            coroutineScope.launch {
                delay(2000)
                buttonState = Result
                delay(4000)
                buttonState = Initial
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        )
    }
}