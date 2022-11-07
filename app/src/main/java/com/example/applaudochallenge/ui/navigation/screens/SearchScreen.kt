package com.example.applaudochallenge.ui.navigation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.applaudochallenge.R
import com.example.applaudochallenge.ui.theme.dimension

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
internal fun SearchScreen(
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
    val searchDefault = stringResource(id = R.string.search)
    val textFieldValue = rememberSaveable { mutableStateOf(searchDefault) }

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.dimension.sizeDp48),
        title = {
            TopBarSearchField(textFieldValue)
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
        backgroundColor = colorResource(id = R.color.primary_color)
    )
}

@Composable
private fun TopBarSearchField(textFieldValue: MutableState<String>) {
    Card(
        modifier = Modifier
            .padding(
                top = MaterialTheme.dimension.sizeDp8,
                bottom = MaterialTheme.dimension.sizeDp8,
                end = MaterialTheme.dimension.sizeDp8,
            )
            .fillMaxSize(),
        shape = RoundedCornerShape(MaterialTheme.dimension.sizeDp8),
        elevation = MaterialTheme.dimension.sizeDp8
    ) {
        val source = remember { MutableInteractionSource() }
        val searchDefault = stringResource(id = R.string.search)
        if (source.collectIsPressedAsState().value && textFieldValue.value == searchDefault) {
            textFieldValue.value = ""
        }

        BasicTextField(
            interactionSource = source,
            modifier = Modifier
                .padding(MaterialTheme.dimension.sizeDp4)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(MaterialTheme.dimension.sizeDp8)
                ),
            value = textFieldValue.value,
            onValueChange = { newText ->
                textFieldValue.value = newText
            }
        )
    }

}