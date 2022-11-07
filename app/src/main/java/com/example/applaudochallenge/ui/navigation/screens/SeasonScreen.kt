package com.example.applaudochallenge.ui.navigation.screens

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.applaudochallenge.ui.PageWithState
import com.example.applaudochallenge.utilities.getImageByPath
import com.example.applaudochallenge.data.models.tvshowdetails.season.episode.Episode
import com.example.applaudochallenge.ui.navigation.viewmodels.SeasonScreenViewModel
import com.example.applaudochallenge.ui.theme.dimension
import com.example.applaudochallenge.ui.widgets.BackButton

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun SeasonScreen(
    viewModel: SeasonScreenViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val lazyListState = rememberLazyListState()
    val loadingSeasonUiState by viewModel.uiStateSeason.collectAsStateWithLifecycle()
    val whenItemVisible by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex >= 1
        }
    }

    PageWithState<List<Episode>>(
        loadingUiState = loadingSeasonUiState,
    ) { episodes ->
        Scaffold(
            modifier = Modifier.fillMaxHeight(),
            topBar = {
                AnimatedVisibility(
                    visible = whenItemVisible,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    TopBar(
                        titleText = stringResource(R.string.season),
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
                    episodes.forEach { episodes ->
                        EpisodeView(
                            episode = episodes
                        )
                    }
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
internal fun EpisodeView(
    episode: Episode
) {
    Card(
        modifier = Modifier
            .height(MaterialTheme.dimension.sizeDp144)
            .fillMaxWidth()
            .padding(MaterialTheme.dimension.sizeDp8),
        shape = RoundedCornerShape(MaterialTheme.dimension.sizeDp10),
        elevation = MaterialTheme.dimension.sizeDp2,
    ) {
        Row {
            episode.poster_path?.let {
                ImageView(url = it)
            }
            ContentView(season = episode)
        }
    }
}

@Composable
private fun ContentView(
    modifier: Modifier = Modifier,
    season: Episode,
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
            text = season.overview,
            fontSize = 12.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            color = colorResource(id = R.color.subtle_text_color)
        )
        Text(
            modifier = modifier.fillMaxWidth(),
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = colorResource(id = R.color.primary_color),
            text = "Episode ${season.episode_number}}"
        )
    }
}

@Composable
private fun ImageView(modifier: Modifier = Modifier, url: String) {
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