package com.example.applaudochallenge.ui;

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.applaudochallenge.R

@Composable
internal fun TopBar(
    onSearchClick: () -> Unit,
    onProfileClick: () -> Unit
) {

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        title = {
            Text(
                text = stringResource(id = R.string.main_screen_title),
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        },
        actions = {
            IconButton(
                onClick = onSearchClick
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = Color.White,
                    contentDescription = null
                )
            }
            IconButton(
                onClick = onProfileClick
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        },
        backgroundColor = MaterialTheme.colors.primary
    )
}