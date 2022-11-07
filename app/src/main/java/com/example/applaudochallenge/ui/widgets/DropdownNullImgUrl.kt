package com.example.applaudochallenge.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.applaudochallenge.R
import com.example.applaudochallenge.ui.theme.dimension

@Composable
internal fun DropdownNullImgUrl(modifier: Modifier) {
    Box(
        modifier = ImageSectionSize(modifier),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(R.drawable.ic_loading_error),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(MaterialTheme.dimension.sizeDp48),
        )
    }
}

@Composable
internal fun ImageSectionSize(modifier: Modifier) = modifier
    .fillMaxWidth()
    .height(MaterialTheme.dimension.sizeDp144)