package com.example.paybackchallenge.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import com.example.paybackchallenge.R
import com.example.paybackchallenge.domain.enum.SwipingStates
import com.example.paybackchallenge.ui.animations.AvailableSpaceView
import com.example.paybackchallenge.ui.component.LazyItemsRow
import com.example.paybackchallenge.ui.component.StatisticsTopBar
import com.example.paybackchallenge.ui.component.nearbyPointsList
import com.example.paybackchallenge.ui.main.MainModel
import com.example.paybackchallenge.ui.theme.Primary


@OptIn(ExperimentalMaterialApi::class, ExperimentalMotionApi::class)
@Composable
fun HomeScreen(homeModel: HomeScreenViewModel, mainModel: MainModel) {

    val mainState = mainModel.state
    val homeState = homeModel.state
    val context = LocalContext.current
    val realAvailable = 1f - 0.7f
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene).readBytes().decodeToString()
    }
    if (homeState.loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    else {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
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

                        Box(
                            modifier = Modifier
                                .layoutId("body")
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .background(Color.White, RoundedCornerShape(12.dp))
                        ) {
                            //Merged lazy column with lazy row and lazy column inside
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(1) {
                                    StatisticsTopBar(stringResource(R.string.statistics))
                                    Spacer(Modifier.height(24.dp))
                                }
//                                item { LazyItemsRow(homeState.car) }

                                 nearbyPointsList(homeState.listSuperCharges,homeState.imagesList)
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
                            text = stringResource(
                                R.string.good_morning,
                                mainState.user?.firstName ?: ""
                            ),
                            color = Color.White,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .layoutId("welcome_message")
                                .alpha(alpha = 1f - computedProgress)

                        )
                        Text(
                            text = stringResource(R.string.charging_car),
                            color = Color.White,
                            style = MaterialTheme.typography.h1,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .layoutId("charging_message")
                                .alpha(alpha = 1f - computedProgress)

                        )
                        Image(
                            painter = painterResource(R.drawable.tesla_x_white),
                            contentDescription = "My car",
                            modifier = Modifier
                                .width(250.dp)
                                .height(80.dp)
                                .layoutId("content1")
                        )


                        Text(
                            text = stringResource(R.string.time_to_end, 49.toString()),
                            color = Color.White,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .layoutId("time_charge")
                                .alpha(alpha = 1f - computedProgress)
                        )


                        Column(
                            modifier = Modifier.layoutId("content3")
                        ) {
                            Text(
                                text = homeState.car?.carModel ?: "",
                                style = MaterialTheme.typography.h3,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                            )
                            Text(
                                text = stringResource(R.string.time_to_end_second, 49.toString()),
                                color = Color.White,
                                style = MaterialTheme.typography.body2,
                            )
                        }
                    }
                }
            }
        }
    }
}