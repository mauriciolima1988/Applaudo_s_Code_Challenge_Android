package com.example.applaudochallenge.ui.profile

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
import com.example.applaudochallenge.database.FavoriteTvShow
import com.example.applaudochallenge.ui.CustomAlertDialog
import com.example.applaudochallenge.ui.navigation.components.ImageViewSection
import com.example.applaudochallenge.ui.navigation.components.RatingViewSection
import com.example.applaudochallenge.ui.navigation.components.TitleViewSection

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ProfileScreen(
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
            .height(48.dp),
        title = {
            Text(
                text = stringResource(id = R.string.profile),
                color = Color.White
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
fun LogOutButtonSection(showLeaveDialog: MutableState<Boolean>) {
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .size(48.dp),
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
fun FavoriteSection(favoritesList: List<FavoriteTvShow>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.my_favorites),
            fontWeight = FontWeight.Bold
        )
        if (favoritesList.isEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
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
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                items(favoritesList) { favTvShow ->
                    FavoritesListItem(favoriteTvShow = favTvShow)
                }
            }
        }
    }
}

@Composable
fun FavoritesListItem(
    modifier: Modifier = Modifier,
    favoriteTvShow: FavoriteTvShow
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .size(width = 192.dp, height = 244.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column {
            ImageViewSection(imageUrl = favoriteTvShow.poster_path)
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                TitleViewSection(tvShowName = favoriteTvShow.name)
                Spacer(modifier = Modifier.height(8.dp))
                RatingViewSection(tvShowScoreRating = favoriteTvShow.vote_average)
            }
        }
    }
}

@Composable
fun ProfileSection() {
    Spacer(modifier = Modifier.height(32.dp))
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
                    .size(128.dp)
                    .border(
                        16.dp,
                        color = colorResource(id = R.color.primary_semitransparent_color),
                        CircleShape
                    )
                    .border(4.dp, color = Color.Transparent, CircleShape),
            ) {
                Icon(
                    modifier = Modifier.size(80.dp),
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
            FloatingActionButton(
                modifier = Modifier.size(32.dp),
                backgroundColor = MaterialTheme.colors.primary,
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Default.Edit,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.profile_mock_name),
        )
        Text(
            text = stringResource(id = R.string.profile_mock_username),
            fontSize = 10.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}