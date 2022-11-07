package com.example.applaudochallenge.ui.navigation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.applaudochallenge.data.models.TvShow
import com.example.applaudochallenge.ui.theme.dimension

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun TvShowItemsList(
    modifier: Modifier = Modifier,
    tvShow: TvShow,
    onCardClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .padding(MaterialTheme.dimension.sizeDp8)
            .wrapContentSize(align = Alignment.Center),
        shape = RoundedCornerShape(MaterialTheme.dimension.sizeDp10),
        elevation = MaterialTheme.dimension.sizeDp10,
        onClick = { onCardClick(tvShow.id) }
    ) {
        Column {
            ImageViewSection(imageUrl = tvShow.poster_path)
            Spacer(modifier = Modifier.height(MaterialTheme.dimension.sizeDp8))
            Column(
                modifier = Modifier.padding(MaterialTheme.dimension.sizeDp8)
            ) {
                TitleViewSection(tvShowName = tvShow.name)
                Spacer(modifier = Modifier.height(MaterialTheme.dimension.sizeDp8))
                RatingViewSection(tvShowScoreRating = tvShow.vote_average)
                Spacer(modifier = Modifier.height(MaterialTheme.dimension.sizeDp8))
            }
        }
    }
}