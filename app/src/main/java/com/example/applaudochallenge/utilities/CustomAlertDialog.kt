package com.example.applaudochallenge.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.applaudochallenge.R
import com.example.applaudochallenge.ui.theme.dimension

@Composable
internal fun CustomAlertDialog(
    message: String,
    positiveText: String,
    negativeText: String,
    showDialog: MutableState<Boolean>,
    onAction: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { showDialog.value = false },
        text = { Text(message) },
        confirmButton = {
            ClickableText(
                text = AnnotatedString(positiveText),
                style = TextStyle(
                    color = colorResource(id = R.color.primary_color),
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(MaterialTheme.dimension.sizeDp16),
                onClick = {
                    showDialog.value = false
                })
        },
        dismissButton = {
            ClickableText(
                text = AnnotatedString(negativeText),
                style = TextStyle(
                    color = colorResource(id = R.color.error_color),
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(MaterialTheme.dimension.sizeDp16),
                onClick = {
                    showDialog.value = false
                    onAction()
                })
        }
    )
}
