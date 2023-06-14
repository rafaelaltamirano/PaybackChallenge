package com.example.paybackchallenge.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.paybackchallenge.R
import com.example.paybackchallenge.domain.entities.Image
import com.example.paybackchallenge.ui.component.TopBar
import com.example.paybackchallenge.ui.theme.Primary

@Composable
fun DetailScreen(itemImage: Image?, onBack: () -> Unit) {
    val scrollState = rememberScrollState()
    val itemSize: Dp = (LocalConfiguration.current.screenHeightDp.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
    ) {
        TopBar(stringResource(R.string.details), onBack = onBack)
        Image(
            modifier = Modifier
                .heightIn(max = itemSize / 2)
                .fillMaxWidth(),
            painter = rememberAsyncImagePainter(itemImage?.largeImageURL),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Spacer(Modifier.height(12.dp))
        ProfileProperty("User Name:", itemImage?.user ?: "")
        ProfileProperty("Tags:", itemImage?.tags ?: "")
        ProfileProperty("Likes", itemImage?.likes.toString())
        ProfileProperty("Downloads", itemImage?.downloads.toString())
        ProfileProperty("Comments", itemImage?.comments.toString())
    }
}

@Composable
fun ProfileProperty(label: String, value: String) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider(modifier = Modifier.padding(bottom = 4.dp))
        Text(
            text = label,
            modifier = Modifier.height(24.dp),
            style = MaterialTheme.typography.caption,
            color = Color.Black
        )
        Text(
            text = value,
            modifier = Modifier.height(24.dp),
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Visible,
            color = Color.Black
        )
    }
}