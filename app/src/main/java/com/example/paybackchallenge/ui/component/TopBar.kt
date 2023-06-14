package com.example.paybackchallenge.ui.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.example.paybackchallenge.ui.theme.Primary

@Composable
fun TopBar (title: String, onBack: ()-> Unit){

    TopAppBar(
        backgroundColor = Primary,
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = "Back"
                )
            }
        },
    )
}