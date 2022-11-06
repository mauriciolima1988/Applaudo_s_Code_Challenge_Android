package com.example.applaudochallenge.ui.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.applaudochallenge.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = { SearchBar(onBackPressed) }
    ) {

    }
}

@Composable
private fun SearchBar(
    onBackPressed: () -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        title = {
            // TODO insert Edit Text
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
        backgroundColor = colorResource(id = R.color.primary_color)
    )
}