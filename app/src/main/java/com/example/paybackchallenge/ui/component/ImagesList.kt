package com.example.paybackchallenge.ui.component


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.paybackchallenge.domain.entities.Image
import com.example.paybackchallenge.ui.router.RouterDir
import java.util.*


@OptIn(ExperimentalLayoutApi::class)
fun LazyListScope.imagesList(imagesList: List<Image>, navController: NavHostController,onClick : ((Image) -> Unit)) {

    item {
        FlowRow(
            maxItemsInEachRow = 2,
        ) {
            val itemModifier = Modifier
                .padding(4.dp)
                .height(130.dp)
                .weight(1f)

            repeat(imagesList.size) {
                ImageItem(
                    modifier = itemModifier,
                    imagesList = imagesList,
                    position = it,
                    onClick = {image ->
                        onClick(image)}
                )
            }
        }
    }
}
