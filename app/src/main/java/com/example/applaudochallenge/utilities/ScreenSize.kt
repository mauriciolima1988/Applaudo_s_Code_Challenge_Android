package com.example.applaudochallenge.utilities

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

data class ScreenSize(val horizontalSize: HorizontalSize) {
    sealed class HorizontalSize {
        object Normal : HorizontalSize()
        object Large : HorizontalSize()
        object ExtraLarge : HorizontalSize()
    }
}

@Composable
fun rememberWindowSize(): ScreenSize {

    val localConfiguration = LocalConfiguration.current

    return ScreenSize(
        horizontalSize = when {
            localConfiguration.screenWidthDp <= 600 -> ScreenSize.HorizontalSize.Normal
            localConfiguration.screenWidthDp <= 900 -> ScreenSize.HorizontalSize.Large
            else -> ScreenSize.HorizontalSize.ExtraLarge
        }
    )
}