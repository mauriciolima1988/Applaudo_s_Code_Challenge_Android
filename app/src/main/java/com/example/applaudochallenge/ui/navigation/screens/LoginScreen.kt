package com.example.applaudochallenge.ui.navigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applaudochallenge.R
import com.example.applaudochallenge.ui.theme.dimension

@Composable
internal fun LoginScreen(
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppLogo()
        AppName()
        LoginMessage()
        LoginButton(onClick = onLoginClick)
    }
}

@Composable
internal fun AppLogo(){
    Image(
        painterResource(R.drawable.app_logo),
        contentDescription = stringResource(R.string.mubi),
        modifier = Modifier.size(96.dp)
    )
}

@Composable
internal fun AppName(){
    Text(
        modifier = Modifier.padding(bottom = MaterialTheme.dimension.sizeDp8),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 53.sp,
        color = colorResource(id = R.color.primary_color),
        text = stringResource(R.string.mubi)
    )
}

@Composable
internal fun LoginMessage(){
    Text(
        modifier = Modifier.padding(MaterialTheme.dimension.sizeDp16),
        color = colorResource(id = R.color.text_color),
        text = stringResource(R.string.sign_in_text)
    )
}

@Composable
internal fun LoginButton(onClick: () -> Unit) {
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
            onClick = onClick
        ) {
            Text(
                text = stringResource(id = R.string.logint_button_text),
                color = Color.White
            )
        }
    }
}