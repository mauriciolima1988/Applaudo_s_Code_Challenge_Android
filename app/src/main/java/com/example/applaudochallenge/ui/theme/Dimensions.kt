package com.example.applaudochallenge.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val sizeDp2: Dp = 2.dp,
    val sizeDp4: Dp = 4.dp,
    val sizeDp8: Dp = 8.dp,
    val sizeDp10: Dp = 10.dp,
    val sizeDp16: Dp = 16.dp,
    val sizeDp24: Dp = 24.dp,
    val sizeDp32: Dp = 32.dp,
    val sizeDp48: Dp = 48.dp,
    val sizeDp80: Dp = 80.dp,
    val sizeDp128: Dp = 128.dp,
    val sizeDp144: Dp = 144.dp,
    val sizeDp192: Dp = 192.dp,
    val sizeDp244: Dp = 244.dp,
)

val LocalDimension = compositionLocalOf { Dimensions() }

val MaterialTheme.dimension: Dimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalDimension.current