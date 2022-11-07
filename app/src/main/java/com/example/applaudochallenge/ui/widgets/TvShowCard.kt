package com.example.applaudochallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.example.applaudochallenge.R
import com.example.applaudochallenge.ui.theme.ApplaudoChallengeTheme
import com.example.applaudochallenge.ui.theme.dimension
import com.example.applaudochallenge.ui.widgets.RatingBar

@Composable
fun TvShowCard(
    name: String,
    rating: Double,
    url: String
) {
    val painter = rememberAsyncImagePainter(model = url)

    Card(
        modifier = Modifier
            .padding(MaterialTheme.dimension.sizeDp8)
            .wrapContentSize()
            .clickable { },
        shape = RoundedCornerShape(MaterialTheme.dimension.sizeDp8),
        elevation = MaterialTheme.dimension.sizeDp2
    ) {
        Column(
        ) {
            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier,
                contentScale = ContentScale.Crop,
            )

            Text(
                text = name,
                modifier = Modifier.padding(
                    start = MaterialTheme.dimension.sizeDp8,
                    end = MaterialTheme.dimension.sizeDp8,
                    top = MaterialTheme.dimension.sizeDp16,
                ),
                color = colorResource(id = R.color.tvshow_card_title_color),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Medium
            )

            Row(
                modifier = Modifier.padding(
                    start = MaterialTheme.dimension.sizeDp8,
                    end = MaterialTheme.dimension.sizeDp8,
                    top = MaterialTheme.dimension.sizeDp16,
                ),
                horizontalArrangement = Arrangement.SpaceEvenly,

                ) {
                RatingBar(
                    modifier = Modifier
                        .padding(top = MaterialTheme.dimension.sizeDp4),
                    rating = rating,
                )
                Text(
                    text = rating.toString(),
                    color = colorResource(id = R.color.tvshow_card_rating_color),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(
                            start = MaterialTheme.dimension.sizeDp8,
                            end = MaterialTheme.dimension.sizeDp8
                        ),
                )
            }
        }
    }
}

@Preview("default")
@Composable
fun TvShowCardPreview() {
    ApplaudoChallengeTheme() {
        TvShowCard(
            name = "TV Show Name",
            rating = 5.0,
            url = "https://picsum.photos/300/300"
        )
    }
}
