package com.example.applaudochallenge.ui.navigation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.applaudochallenge.R
import com.example.applaudochallenge.ui.theme.dimension
import com.example.applaudochallenge.ui.widgets.DropdownNullImgUrl
import com.example.applaudochallenge.ui.widgets.ImageSectionSize
import com.example.applaudochallenge.utilities.getImageByPath

@Composable
internal fun ImageViewSection(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    val context = LocalContext.current

    if (imageUrl == "") {
        DropdownNullImgUrl(modifier)
        return
    }

    SubcomposeAsyncImage(
        modifier = ImageSectionSize(modifier),
        model = ImageRequest.Builder(context)
            .data(imageUrl.getImageByPath())
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_loading_error)
            .build(),
        contentDescription = null,
        loading = {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    Modifier
                        .size(MaterialTheme.dimension.sizeDp24)
                        .align(Alignment.Center)
                )
            }
        },
        contentScale = ContentScale.Crop
    )
}