package com.example.applaudochallenge.ui.navigation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.applaudochallenge.R
import com.example.applaudochallenge.data.database.FavoriteTvShow
import com.example.applaudochallenge.data.network.LoadingUiState
import com.example.applaudochallenge.ui.PageWithState
import com.example.applaudochallenge.ui.widgets.RatingBar
import com.example.applaudochallenge.utilities.getImageByPath
import com.example.applaudochallenge.data.models.TvShowDetails
import com.example.applaudochallenge.data.models.tvshowdetails.ShortSeason
import com.example.applaudochallenge.ui.navigation.viewmodels.DetailsScreenViewModel
import com.example.applaudochallenge.ui.theme.dimension
import com.example.applaudochallenge.ui.widgets.BackButton
import com.example.applaudochallenge.ui.widgets.DropdownNullImgUrl

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun DetailsScreen(
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    onSeasonClick: (Int, Int) -> Unit,
    onBackPressed: () -> Unit
) {

    val loadingUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isInFavorites by viewModel.isInFavorites.collectAsStateWithLifecycle()

    DetailsView(
        isInFavorites = isInFavorites,
        loadingUiState = loadingUiState,
        onFavoriteButtonClick = { tvShowDetails ->
            insertInFavoritesClickEvent(viewModel, tvShowDetails, isInFavorites)
        },
        onSeasonClick = onSeasonClick,
        onBackPressed = onBackPressed,
    )
}

@Composable
private fun DetailsView(
    modifier: Modifier = Modifier,
    loadingUiState: LoadingUiState?,
    isInFavorites: Boolean,
    onFavoriteButtonClick: (TvShowDetails) -> Unit,
    onSeasonClick: (Int, Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val lazyListState = rememberLazyListState()
    val whenItemVisible by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex >= 1
        }
    }

    PageWithState<TvShowDetails>(
        loadingUiState = loadingUiState,
    ) { tvShowDetails ->

        Scaffold(
            modifier = modifier.fillMaxHeight(),
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
                        imageUrl = tvShowDetails.backdrop_path ?: "",
                        name = tvShowDetails.name,
                        originalName = tvShowDetails.original_name,
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
                        tvShowId = tvShowDetails.id,
                        seasons = tvShowDetails.seasons,
                        onSeasonClick = onSeasonClick
                    )
                }
            }
            if (!whenItemVisible) {
                BackButton(
                    modifier = Modifier
                        .size(MaterialTheme.dimension.sizeDp48)
                        .padding(MaterialTheme.dimension.sizeDp4),
                    onBackPressed = onBackPressed
                )
            }
        }
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    titleText: String,
    onBackPressed: () -> Unit
) {

    TopAppBar(
        modifier = modifier
            .height(MaterialTheme.dimension.sizeDp48)
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
                modifier = Modifier.size(MaterialTheme.dimension.sizeDp32),
                onClick = onBackPressed
            ) {
                Icon(
                    modifier = Modifier.size(MaterialTheme.dimension.sizeDp24),
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
private fun HeaderSection(
    modifier: Modifier = Modifier,
    imageUrl: String,
    name: String,
    originalName: String,
    average: Double,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomStart
    ) {

        if (imageUrl == "") {
            DropdownNullImgUrl(modifier)
        } else {
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
        }
        Column(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.dimension.sizeDp16,
                    top = MaterialTheme.dimension.sizeDp16,
                    end = MaterialTheme.dimension.sizeDp16,
                    bottom = MaterialTheme.dimension.sizeDp6,
                ),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = originalName,
                color = Color.White,
            )
            Text(
                text = name,
                color = Color.White,
                fontWeight = FontWeight.Light,
                fontSize = 34.sp
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimension.sizeDp10))
            RatingBar(
                rating = average,
                starsColor = MaterialTheme.colors.secondary
            )
        }
    }
}

@Composable
internal fun SummaryView(
    tvShowDetails: TvShowDetails,
    isInFavorites: Boolean,
    onFavoriteClick: (TvShowDetails) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(MaterialTheme.dimension.sizeDp16),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {

        if (tvShowDetails.overview.isNotEmpty()) {
            Spacer(modifier = Modifier.height(MaterialTheme.dimension.sizeDp8))
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
            Spacer(modifier = Modifier.height(MaterialTheme.dimension.sizeDp8))
            Text(text = tvShowDetails.overview)
            Spacer(modifier = Modifier.height(MaterialTheme.dimension.sizeDp16))
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
        modifier = Modifier.size(MaterialTheme.dimension.sizeDp24),
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
            modifier = Modifier.size(MaterialTheme.dimension.sizeDp24),
            tint = MaterialTheme.colors.primary,
            contentDescription = null
        )
    }
}

@Composable
internal fun SeasonsSection(
    tvShowId: Int,
    seasons: List<ShortSeason>,
    onSeasonClick: (Int, Int) -> Unit
) {
    seasons.forEach { season ->
        SeasonView(
            tvShowId = tvShowId,
            season = season,
            onSeasonClick = onSeasonClick
        )
    }
}

@Composable
internal fun SeasonView(tvShowId: Int, season: ShortSeason, onSeasonClick: (Int, Int) -> Unit ) {
    Card(
        modifier = Modifier
            .height(MaterialTheme.dimension.sizeDp144)
            .fillMaxWidth()
            .padding(MaterialTheme.dimension.sizeDp8)
            .clickable {
                onSeasonClick(tvShowId, season.id)
            },
        shape = RoundedCornerShape(MaterialTheme.dimension.sizeDp10),
        elevation = MaterialTheme.dimension.sizeDp2,
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
            .padding(MaterialTheme.dimension.sizeDp16),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = modifier.fillMaxWidth(),
            text = season.name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = colorResource(id = R.color.text_color),
        )
        Text(
            modifier = modifier.fillMaxWidth(),
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = colorResource(id = R.color.primary_color),
            text = "${season.episode_count} ${stringResource(id = R.string.episodes)}"
        )
        Text(
            modifier = modifier.fillMaxWidth(),
            text = season.overview,
            fontSize = 12.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            color = colorResource(id = R.color.subtle_text_color)
        )
    }
}

@Composable
private fun ImageView(modifier: Modifier = Modifier, url: String, ) {
    val context = LocalContext.current
    SubcomposeAsyncImage(
        modifier = modifier
            .fillMaxHeight()
            .width(MaterialTheme.dimension.sizeDp128),
        model = ImageRequest.Builder(context)
            .data(url.getImageByPath())
            .build(),
        contentDescription = null,
        alignment = Alignment.TopStart,
        contentScale = ContentScale.FillWidth
    )
}

private fun insertInFavoritesClickEvent(
    viewModel: DetailsScreenViewModel,
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
