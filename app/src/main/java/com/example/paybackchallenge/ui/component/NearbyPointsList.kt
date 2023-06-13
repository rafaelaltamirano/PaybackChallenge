package com.example.paybackchallenge.ui.component


import android.R.attr.maxLines
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.paybackchallenge.domain.entities.Image
import com.example.paybackchallenge.domain.entities.SuperCharges
import java.util.*


@OptIn(ExperimentalLayoutApi::class)
fun LazyListScope.nearbyPointsList(item: List<SuperCharges>, imagesList: List<Image>) {

    item {
        val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2)
        FlowRow(
            maxItemsInEachRow = 2,
        ) {
            val itemModifier = Modifier
                .padding(4.dp)
                .height(130.dp)
                .weight(1f)

            repeat(imagesList.size) {

                Card(
                    shape = RoundedCornerShape(10),
                    elevation = 4.dp,
                    modifier = itemModifier,
                    border = BorderStroke(5.dp, Color.White),
                ) {
                    Column(
                        modifier = Modifier.padding(3.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(imagesList[it].previewURL),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.7f)
                                .clip(shape = RoundedCornerShape(10.dp)),
                        )
                        Column(modifier = Modifier.weight(0.3f).padding(3.dp)) {
                            Text(
                                text = imagesList[it].user.replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(
                                        Locale.ROOT
                                    ) else it.toString()
                                },
                                color = Color.Black,
                                style = MaterialTheme.typography.h3,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = imagesList[it].tags,
                                color = Color.Black,
                                style = MaterialTheme.typography.caption,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}
