package com.example.paybackchallenge.ui.component


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.paybackchallenge.domain.entities.Image
import com.example.paybackchallenge.ui.router.RouterDir
import com.example.paybackchallenge.ui.screens.home.HomeViewModel
import java.util.*


@OptIn(ExperimentalLayoutApi::class)
fun LazyListScope.imagesList(imagesList: LazyPagingItems<Image>, navController: NavHostController, onClick : ((Image) -> Unit)) {


//    item  {
//        FlowRow(
//            maxItemsInEachRow = 2,
//        ) {
//            val itemModifier = Modifier
//                .padding(4.dp)
//                .height(130.dp)
//                .weight(1f)
//
//            repeat(imagesList.itemCount) {
//                ImageItem(
//                    modifier = itemModifier,
//                    imagesList = imagesList,
//                    position = it,
//                    onClick = {image ->
//                        onClick(image)}
//                )
//            }
//
//            when (val state = imageslist.loadState.refresh) { //FIRST LOAD
//                is LoadState.Error -> {
//                    //TODO Error Item
//                    //state.error to get error message
//                }
//                is LoadState.Loading -> { // Loading UI
//                    item {
//                        Column(
//                            modifier = Modifier.fillParentMaxSize(),
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.Center,
//                        ) {
//                            Text(
//                                modifier = Modifier.padding(8.dp),
//                                text = "Refresh Loading",
//                                color = Color.Black
//                            )
//
//                            CircularProgressIndicator(color = Color.Black)
//                        }
//                    }
//                }
//                else -> {}
//            }
//
//            when (val state = imageslist.loadState.append) { // Pagination
//                is LoadState.Error -> {
//                    //TODO Pagination Error Item
//                    //state.error to get error message
//                }
//                is LoadState.Loading -> { // Pagination Loading UI
//                    item {
//                        Column(
//                            modifier = Modifier.fillMaxWidth(),
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.Center,
//                        ) {
//                            Text(text = "Pagination Loading",  color = Color.Black)
//
//                            CircularProgressIndicator(color = Color.Black)
//                        }
//                    }
//                }
//                else -> {}
//            }
//        }
//        }
//    }
}
