package com.example.applaudochallenge.ui.navigation.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.applaudochallenge.R
import com.example.applaudochallenge.data.database.FavoriteTvShow
import com.example.applaudochallenge.ui.CustomAlertDialog
import com.example.applaudochallenge.ui.navigation.components.ImageViewSection
import com.example.applaudochallenge.ui.navigation.components.RatingViewSection
import com.example.applaudochallenge.ui.navigation.components.TitleViewSection
import com.example.applaudochallenge.ui.navigation.viewmodels.ProfileScreenViewModel
import com.example.applaudochallenge.ui.theme.dimension

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun ProfileScreen(
    viewModel: ProfileScreenViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val favoritesList by viewModel.favoritesList.collectAsStateWithLifecycle()

    val showLeaveDialog = rememberSaveable { mutableStateOf(false) }

    if (showLeaveDialog.value) {
        CustomAlertDialog(
            message = stringResource(id = R.string.leave_dialog_message),
            positiveText = stringResource(id = R.string.stay_dialog_button_text),
            negativeText = stringResource(id = R.string.leave_dialog_button_text),
            showDialog = showLeaveDialog,
            onAction = {
                onBackPressed()
            })
    }
    ProfileScreen(onBackPressed, favoritesList, showLeaveDialog)
}

@Composable
private fun ProfileScreen(
    onBackPressed: () -> Unit,
    favoritesList: List<FavoriteTvShow>?,
    showLeaveDialog: MutableState<Boolean>
) {

    Scaffold(
        topBar = { ProfileTopBar(onBackPressed) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top
        ) {
            item {
                ProfileSection()
                FavoriteSection(favoritesList ?: emptyList())
                LogOutButtonSection(showLeaveDialog)
            }
        }
    }
}

@Composable
private fun ProfileTopBar(
    onBackPressed: () -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.dimension.sizeDp48),
        title = {
            Text(
                text = stringResource(id = R.string.profile),
                color = Color.White
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
internal fun LogOutButtonSection(showLeaveDialog: MutableState<Boolean>) {
    Spacer(modifier = Modifier.height(MaterialTheme.dimension.sizeDp8))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimension.sizeDp16)
                .size(MaterialTheme.dimension.sizeDp48),
            onClick = {
                showLeaveDialog.value = true
            }
        ) {
            Text(
                text = stringResource(id = R.string.logout_button_text),
                color = Color.White
            )
        }
    }
}

@Composable
internal fun FavoriteSection(favoritesList: List<FavoriteTvShow>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.padding(horizontal = MaterialTheme.dimension.sizeDp16),
            text = stringResource(id = R.string.my_favorites),
            fontWeight = FontWeight.Bold
        )
        if (favoritesList.isEmpty()) {
            Spacer(modifier = Modifier.height(MaterialTheme.dimension.sizeDp16))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.favorites_empty)
                )
            }
        } else {
            Spacer(modifier = Modifier.height(MaterialTheme.dimension.sizeDp8))
            LazyRow(
                modifier = Modifier.padding(horizontal = MaterialTheme.dimension.sizeDp8)
            ) {
                items(favoritesList) { favTvShow ->
                    FavoritesListItem(favoriteTvShow = favTvShow)
                }
            }
        }
    }
}

@Composable
internal fun FavoritesListItem(
    modifier: Modifier = Modifier,
    favoriteTvShow: FavoriteTvShow
) {
    Card(
        modifier = modifier
            .padding(MaterialTheme.dimension.sizeDp8)
            .size(width = MaterialTheme.dimension.sizeDp192, height = MaterialTheme.dimension.sizeDp244),
        shape = RoundedCornerShape(MaterialTheme.dimension.sizeDp8),
        elevation = MaterialTheme.dimension.sizeDp4
    ) {
        Column {
            ImageViewSection(imageUrl = favoriteTvShow.poster_path ?: "")
            Spacer(modifier = Modifier.height(MaterialTheme.dimension.sizeDp8))
            Column(
                modifier = Modifier.padding(MaterialTheme.dimension.sizeDp8)
            ) {
                TitleViewSection(tvShowName = favoriteTvShow.name)
                Spacer(modifier = Modifier.height(MaterialTheme.dimension.sizeDp8))
                RatingViewSection(tvShowScoreRating = favoriteTvShow.vote_average)
            }
        }
    }
}

@Composable
internal fun ProfileSection() {
    Spacer(modifier = Modifier.height(MaterialTheme.dimension.sizeDp32))
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(MaterialTheme.dimension.sizeDp128)
                    .border(
                        MaterialTheme.dimension.sizeDp16,
                        color = colorResource(id = R.color.primary_semitransparent_color),
                        CircleShape
                    )
                    .border(4.dp, color = Color.Transparent, CircleShape),
            ) {
                Icon(
                    modifier = Modifier.size(MaterialTheme.dimension.sizeDp80),
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
/*            FloatingActionButton(
                modifier = Modifier.size(MaterialTheme.dimension.sizeDp32),
                backgroundColor = MaterialTheme.colors.primary,
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier.size(MaterialTheme.dimension.sizeDp16),
                    imageVector = Icons.Default.Edit,
                    tint = Color.White,
                    contentDescription = null
                )
            }*/
        }
        Spacer(modifier = Modifier.height(MaterialTheme.dimension.sizeDp16))
        Text(
            text = stringResource(id = R.string.profile_mock_name),
        )
        Text(
            text = stringResource(id = R.string.profile_mock_username),
            fontSize = 10.sp,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimension.sizeDp16))
    }
}