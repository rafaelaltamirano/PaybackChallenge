package com.example.paybackchallenge.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.paybackchallenge.R
import com.example.paybackchallenge.domain.enum.SwipingStates
import com.example.paybackchallenge.ui.component.*
import com.example.paybackchallenge.ui.main.MainViewModel
import com.example.paybackchallenge.ui.router.RouterDir
import com.example.paybackchallenge.ui.theme.Primary

@OptIn(ExperimentalMaterialApi::class, ExperimentalMotionApi::class)
@Composable
fun HomeScreen(
    homeModel: HomeViewModel, mainModel: MainViewModel, navController: NavHostController
) {
    val mainState = mainModel.state
    val homeState = homeModel.state
    val context = LocalContext.current
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene).readBytes().decodeToString()
    }
    val imagelistt = homeModel.beerPagingFlow.collectAsLazyPagingItems()


    CustomDialog(show = homeState.openDialog,
        onDismissRequest = { homeModel.setDialogState(false) },
        onClick = {
            navController.navigate(RouterDir.DETAILS.route)
            homeModel.setDialogState(false)
        })


    LaunchedEffect(key1 = imagelistt.loadState) {
        if(imagelistt.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (imagelistt.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }


    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
    ) {
        val swipingState = rememberSwipeableState(initialValue = SwipingStates.EXPANDED)
        BoxWithConstraints(//to get the max height
            modifier = Modifier.fillMaxSize()
        ) {
            val heightInPx = with(LocalDensity.current) { maxHeight.toPx() }
            val nestedScrollConnection = remember {
                object : NestedScrollConnection {
                    override fun onPreScroll(
                        available: Offset, source: NestedScrollSource
                    ): Offset {
                        val delta = available.y
                        return if (delta < 0) {
                            swipingState.performDrag(delta).toOffset()
                        } else {
                            Offset.Zero
                        }
                    }

                    override fun onPostScroll(
                        consumed: Offset, available: Offset, source: NestedScrollSource
                    ): Offset {
                        val delta = available.y
                        return swipingState.performDrag(delta).toOffset()
                    }

                    override suspend fun onPostFling(
                        consumed: Velocity, available: Velocity
                    ): Velocity {
                        swipingState.performFling(velocity = available.y)
                        return super.onPostFling(consumed, available)
                    }

                    private fun Float.toOffset() = Offset(0f, this)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .swipeable(
                        state = swipingState, thresholds = { _, _ ->
                            FractionalThreshold(0.05f)//it can be 0.5 in general
                        }, orientation = Orientation.Vertical, anchors = mapOf(
                            0f to SwipingStates.COLLAPSED,//min height is collapsed
                            heightInPx to SwipingStates.EXPANDED,//max height is expanded
                        )
                    )
                    .nestedScroll(nestedScrollConnection)
            ) {
                val computedProgress by remember {//progress value will be decided as par state
                    derivedStateOf {
                        if (swipingState.progress.to == SwipingStates.COLLAPSED) swipingState.progress.fraction
                        else 1f - swipingState.progress.fraction
                    }
                }
                val startHeightNum = 300
                MotionLayout(
                    modifier = Modifier.fillMaxSize(),
                    motionScene = MotionScene(content = motionScene),
                    progress = computedProgress,
                ) {
                    if (homeState.loading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .layoutId("body"),
                            contentAlignment = Alignment.Center

                        ) {
                            CircularProgressIndicator(color = Primary)
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .layoutId("body")
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .background(Color.White, RoundedCornerShape(12.dp))
                        ) {
                                if (imagelistt.loadState.refresh is LoadState.Loading) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.align(Alignment.Center),
                                        color = Color.Black
                                    )
                                } else {
                                    LazyColumn(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.spacedBy(16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {

                                        items(imagelistt.itemCount) {     pos ->
                                            val itemModifier = Modifier
                                        .padding(4.dp)
                                        .height(130.dp)
                                        .fillMaxWidth()

                                            ImageItem(modifier = itemModifier,
                                        imagesList = imagelistt,
                                        position = pos,
                                        onClick = { image ->
                                            homeModel.setDialogState(true)
                                            homeModel.setSelectedItem(image)
                                        })
                                            }

                                        item {
                                            if(imagelistt.loadState.append is LoadState.Loading) {
                                                CircularProgressIndicator(color = Color.Black)
                                            }
                                        }


//                                items(count = imagelistt.itemCount,
//                                    key = { imagelistt[it]?.id ?: 0 }) { pos ->
//                                    val itemModifier = Modifier
//                                        .padding(4.dp)
//                                        .height(130.dp)
//                                        .fillMaxWidth()
//
//                                    ImageItem(modifier = itemModifier,
//                                        imagesList = imagelistt,
//                                        position = pos,
//                                        onClick = { image ->
//                                            homeModel.setDialogState(true)
//                                            homeModel.setSelectedItem(image)
//                                        })
//                                }
//
//                                when (val state = imagelistt.loadState.refresh) { //FIRST LOAD
//                                    is LoadState.Error -> {
//                                        //TODO Error Item
//                                        //state.error to get error message
//                                    }
//                                    is LoadState.Loading -> { // Loading UI
//                                        item {
//                                            Column(
//                                                modifier = Modifier.fillMaxSize(),
//                                                horizontalAlignment = Alignment.CenterHorizontally,
//                                                verticalArrangement = Arrangement.Center,
//                                            ) {
//                                                Text(
//                                                    modifier = Modifier.padding(8.dp),
//                                                    text = "Refresh Loading",
//                                                    color = Color.Black
//                                                )
//
//                                                CircularProgressIndicator(color = Color.Black)
//                                            }
//                                        }
//                                    }
//                                    else -> {}
//                                }
//
//                                when (val state = imagelistt.loadState.append) { // Pagination
//                                    is LoadState.Error -> {
//                                        //TODO Pagination Error Item
//                                        //state.error to get error message
//                                    }
//                                    is LoadState.Loading -> { // Pagination Loading UI
//                                        item {
//                                            Column(
//                                                modifier = Modifier.fillMaxWidth(),
//                                                horizontalAlignment = Alignment.CenterHorizontally,
//                                                verticalArrangement = Arrangement.Center,
//                                            ) {
//                                                Text(text = "Pagination Loading",  color = Color.Black)
//
//                                                CircularProgressIndicator(color = Color.Black)
//                                            }
//                                        }
//                                    }
//                                    else -> {}
//                                }
                                    }

                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .layoutId("header")
                            .fillMaxWidth()
                            .height(startHeightNum.dp)
                            .background(Primary)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .layoutId("canvas")
                    ) {
                        Canvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp)
                        ) {
                            val canvasWidth = size.width
                            val canvasHeight = size.height
                            val formWidth = (canvasWidth * 2)
                            val xPos = canvasWidth / 2

                            drawArc(
                                Color.White,
                                -180f,
                                180f,
                                useCenter = false,
                                size = Size(formWidth, canvasHeight * 4),
                                topLeft = Offset(x = -xPos, y = canvasHeight - 150)
                            )
                        }
                    }
                    Text(
                        text = stringResource(R.string.stunning_image),
                        color = Color.White,
                        style = MaterialTheme.typography.h1,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .layoutId("stunning_image")
                            .alpha(alpha = 1f - computedProgress)

                    )
                    SearchView(modifier = Modifier
                        .width(250.dp)
                        .height(65.dp)
                        .padding(8.dp)
                        .layoutId("content1"),
                        state = textState,
                        placeholder = "Fruits",
                        onDone = { homeModel.requestImages(it) })


                    Text(
                        text = stringResource(R.string.more_than),
                        color = Color.White,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier
                            .layoutId("more_than")
                            .alpha(alpha = 1f - computedProgress)
                    )


                    Column(
                        modifier = Modifier.layoutId("content3")
                    ) {
                        Text(
                            text = stringResource(R.string.looking_for),
                            style = MaterialTheme.typography.h3,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                        Text(
                            text = stringResource(R.string.over_4_million),
                            color = Color.White,
                            style = MaterialTheme.typography.body2,
                        )
                    }
                }
            }
        }
    }
}
