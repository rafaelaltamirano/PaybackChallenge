package com.example.paybackchallenge.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.paybackchallenge.domain.entities.Image
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageItem (modifier :Modifier, imagesList: LazyPagingItems<Image>, position: Int, onClick : ((Image) -> Unit)){

    Card(
        shape = RoundedCornerShape(10),
        elevation = 4.dp,
        modifier = modifier,
        border = BorderStroke(5.dp, Color.White),
        onClick = { imagesList[position]?.let { onClick(it) } },
    ) {
        Column(
            modifier = Modifier.padding(3.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(imagesList[position]?.previewURL),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
                    .clip(shape = RoundedCornerShape(10.dp)),
            )
            Column(modifier = Modifier.weight(0.3f).padding(3.dp)) {
                Text(
                    text = imagesList[position]?.user?.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    } ?: "",
                    color = Color.Black,
                    style = MaterialTheme.typography.h3,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = imagesList[position]?.tags ?: "",
                    color = Color.Black,
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}