package com.example.simplecryptoapp.presentation.coin_list.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString

@Composable
fun OrderCoinsItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
){
    ClickableText(
        modifier = modifier,
        text = AnnotatedString(text),
        style = MaterialTheme.typography.body1,
        onClick = { onClick() }
    )
}