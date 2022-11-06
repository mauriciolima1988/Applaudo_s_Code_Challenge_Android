package com.example.applaudochallenge.ui.navigation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.applaudochallenge.ui.widgets.RatingBar
import java.math.RoundingMode

@Composable
internal fun RatingViewSection(
    modifier: Modifier = Modifier,
    tvShowScoreRating: Double
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RatingBar(rating = (tvShowScoreRating / 2), starsColor = MaterialTheme.colors.secondary)
        Text(
            text = (tvShowScoreRating / 2).toBigDecimal()
                .setScale(1, RoundingMode.UP)
                .toDouble()
                .toString()
        )
    }
}