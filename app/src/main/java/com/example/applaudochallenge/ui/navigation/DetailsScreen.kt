package com.example.applaudochallenge.ui.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.applaudochallenge.R
import com.example.applaudochallenge.database.FavoriteTvShow
import com.example.applaudochallenge.network.LoadingUiState
import com.example.applaudochallenge.ui.DetailsPageWithState
import com.example.applaudochallenge.ui.RatingBar
import com.example.applaudochallenge.ui.getImageByPath
import com.example.applaudochallenge.ui.models.TvShowDetails
import com.example.applaudochallenge.ui.models.TvShowInfos.ShortSeason
import com.example.applaudochallenge.ui.widgets.BackButton

@Composable
fun DetailsView(
    modifier: Modifier = Modifier,
    loadingUiState: LoadingUiState?,
    isInFavorites: Boolean,
    onFavoriteButtonClick: (TvShowDetails) -> Unit,
    onBackPressed: () -> Unit
) {
    val lazyListState = rememberLazyListState()
    val whenItemVisible by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex >= 1
        }
    }

    val paddingValue = 48.dp

    DetailsPageWithState<TvShowDetails>(
        loadingUiState = loadingUiState,
    ) { tvShowDetails ->

        Scaffold(
            modifier = modifier,
            topBar = {
                AnimatedVisibility(
                    visible = whenItemVisible,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    TopBar(
                        titleText = tvShowDetails.name,
                        onBackPressed = onBackPressed
                    )
                }
            }
        ) { paddingValues ->

            LazyColumn(
                modifier = Modifier.padding(paddingValues),
                state = lazyListState
            ) {
                item {
                    HeaderSection(
                        modifier = Modifier,
                        imageUrl = tvShowDetails.backdrop_path,
                        name = tvShowDetails.name,
                        average = (tvShowDetails.vote_average / 2)
                    )
                }
                item {
                    SummaryView(
                        tvShowDetails = tvShowDetails,
                        isInFavorites = isInFavorites,
                        onFavoriteClick = onFavoriteButtonClick
                    )
                }
                item {
                    SeasonsSection(
                        seasons = tvShowDetails.seasons
                    )
                }
            }
            if (!whenItemVisible) {
                BackButton(
                    modifier = Modifier
                        .size(paddingValue)
                        .padding(4.dp),
                    onBackPressed = onBackPressed
                )
            }
        }
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    titleText: String,
    onBackPressed: () -> Unit
) {

    TopAppBar(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        title = {
            Text(
                text = titleText,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(
                modifier = Modifier.size(32.dp),
                onClick = onBackPressed
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.ArrowBack,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        },
        backgroundColor = MaterialTheme.colors.primary
    )
}

@Composable
fun HeaderSection(
    modifier: Modifier = Modifier,
    imageUrl: String,
    name: String,
    average: Double,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomStart
    ) {

        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.5f),
            model = ImageRequest.Builder(context)
                .data(imageUrl.getImageByPath())
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_loading_error)
                .crossfade(true)
                .build(),
            contentDescription = name,
            alignment = Alignment.Center,
            loading = { LinearProgressIndicator() },
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = name,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            RatingBar(
                rating = average,
                starsColor = MaterialTheme.colors.secondary
            )
        }
    }
}

@Composable
fun SummaryView(
    tvShowDetails: TvShowDetails,
    isInFavorites: Boolean,
    onFavoriteClick: (TvShowDetails) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {

        if (tvShowDetails.overview.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.summary),
                    color = MaterialTheme.colors.primary
                )
                FavoritesButton(onFavoriteClick, tvShowDetails, isInFavorites, context)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = tvShowDetails.overview)
            Spacer(modifier = Modifier.height(16.dp))
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                FavoritesButton(onFavoriteClick, tvShowDetails, isInFavorites, context)
            }
        }
    }
}

@Composable
private fun FavoritesButton(
    onFavButtonClick: (TvShowDetails) -> Unit,
    tvShowDetails: TvShowDetails,
    isInFavorites: Boolean,
    context: Context
) {
    val addedFavorites = stringResource(id = R.string.added_favorites)
    val removeFavorites = stringResource(id = R.string.remove_favorites)
    val (isChecked, setChecked) = remember { mutableStateOf(isInFavorites) }

    IconToggleButton(
        modifier = Modifier.size(24.dp),
        checked = isChecked,
        onCheckedChange = {
            setChecked(!isChecked)
            onFavButtonClick(tvShowDetails)
            val text = if (isChecked) removeFavorites else addedFavorites
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        },
    ) {
        Icon(
            imageVector = if (isChecked) Icons.Outlined.Favorite else Icons.Default.FavoriteBorder,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colors.primary,
            contentDescription = null
        )
    }
}

@Composable
fun SeasonsSection(seasons: List<ShortSeason>) {
    seasons.forEach { season -> SeasonView(season = season) }
}

@Composable
fun SeasonView(season: ShortSeason) {
    Card(
        modifier = Modifier
            .height(32.dp)
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
    ) {
        Row {
            season.poster_path?.let {
                ImageView(url = it)
            }
            ContentView(season = season)
        }
    }
}

@Composable
private fun ContentView(
    modifier: Modifier = Modifier,
    season: ShortSeason,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = modifier.fillMaxWidth(),
            text = season.name,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary,
            text = "${season.episode_count} ${stringResource(id = R.string.episodes)}"
        )
        Text(
            modifier = modifier.fillMaxWidth(),
            text = season.overview,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun ImageView(modifier: Modifier = Modifier, url: String, ) {
    val context = LocalContext.current
    SubcomposeAsyncImage(
        modifier = modifier
            .fillMaxHeight()
            .width(128.dp),
        model = ImageRequest.Builder(context)
            .data(url.getImageByPath())
            .build(),
        contentDescription = null,
        alignment = Alignment.TopStart,
        contentScale = ContentScale.FillWidth
    )
}

private fun insertInFavoritesClickEvent(
    viewModel: InfoScreenViewModel,
    tvShowDetails: TvShowDetails,
    isInFavorites: Boolean
) {
    viewModel.tvShowIsInFavorites(tvShowDetails.id)

    when (isInFavorites) {
        true -> {
            viewModel.deleteTvShowFromFavorites(tvShowDetails.id)
        }
        false -> {
            viewModel.insertTvShowInFavorites(
                FavoriteTvShow(
                    id = tvShowDetails.id,
                    name = tvShowDetails.name,
                    first_air_date = tvShowDetails.first_air_date,
                    overview = tvShowDetails.overview,
                    vote_average = tvShowDetails.vote_average,
                    poster_path = tvShowDetails.poster_path
                )
            )
        }
    }
}
