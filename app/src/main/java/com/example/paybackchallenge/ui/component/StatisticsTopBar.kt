package com.example.paybackchallenge.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.paybackchallenge.R

@Composable
fun StatisticsTopBar(
    title: String,
    indicatorText: String? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h2,
            color = Color.Black
        )
        Spacer(
            Modifier
                .weight(1f)
                .fillMaxHeight()
        )
        Text(
            modifier = Modifier.alpha(0.6f),
            text = indicatorText ?: "",
            color = Color.Black,
            style = MaterialTheme.typography.caption
        )

    }
}