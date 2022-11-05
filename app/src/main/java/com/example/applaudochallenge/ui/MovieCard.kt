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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.applaudochallenge.R
import com.example.applaudochallenge.ui.theme.ApplaudoChallengeTheme

@Composable
fun MovieCard(
    name: String,
    rating: Double,
    url: String
) {
    val painter = rememberImagePainter(data = url)

    Card(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentSize()
            .clickable { },
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp
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
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp),
                color = colorResource(id = R.color.movie_card_title_color),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Medium
            )

            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 8.dp, end = 8.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,

                ) {
                RatingBar(
                    modifier = Modifier
                        .padding(top = 4.dp),
                    rating = rating,
                )
                Text(
                    text = rating.toString(),
                    color = colorResource(id = R.color.movie_card_rating_color),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp),
                )
            }
        }
    }
}

@Preview("default")
@Composable
fun MovieCardPreview() {
    ApplaudoChallengeTheme() {
        MovieCard(
            name = "Movie Name",
            rating = 5.0,
            url = "https://picsum.photos/300/300"
        )
    }
}
