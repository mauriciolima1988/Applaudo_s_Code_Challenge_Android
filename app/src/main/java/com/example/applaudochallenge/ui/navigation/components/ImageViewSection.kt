package com.example.applaudochallenge.ui.navigation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.applaudochallenge.R
import com.example.applaudochallenge.utilities.getImageByPath

@Composable
fun ImageViewSection(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    val context = LocalContext.current

    SubcomposeAsyncImage(
        modifier = modifier
            .fillMaxWidth()
            .height(144.dp),
        model = ImageRequest.Builder(context)
            .data(imageUrl.getImageByPath())
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_loading_error)
            .build(),
        contentDescription = null,
        loading = {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.size(24.dp).align(Alignment.Center))
            }
        },
        contentScale = ContentScale.Crop
    )
}