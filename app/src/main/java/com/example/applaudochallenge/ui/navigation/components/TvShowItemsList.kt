package com.example.applaudochallenge.ui.navigation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.applaudochallenge.ui.models.TvShow

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun TvShowItemsList(
    modifier: Modifier = Modifier,
    tvShow: TvShow,
    onCardClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .wrapContentSize(align = Alignment.Center),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        onClick = { onCardClick(tvShow.id) }
    ) {
        Column {
            ImageViewSection(imageUrl = tvShow.poster_path)
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                TitleViewSection(tvShowName = tvShow.name)
                Spacer(modifier = Modifier.height(8.dp))
                RatingViewSection(tvShowScoreRating = tvShow.vote_average)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}