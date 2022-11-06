package com.example.applaudochallenge.ui.navigation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
internal fun TitleViewSection(
    modifier: Modifier = Modifier,
    tvShowName: String
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = tvShowName.capitalize(Locale.current),
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Start,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}