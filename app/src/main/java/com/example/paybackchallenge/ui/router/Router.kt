package com.example.paybackchallenge.ui.router

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.paybackchallenge.ui.component.DefineBottomNav
import com.example.paybackchallenge.ui.main.MainModel
import com.example.paybackchallenge.ui.router.RouterDir.*
import com.example.paybackchallenge.ui.screens.home.HomeScreen
import com.example.paybackchallenge.ui.screens.home.HomeScreenViewModel

@Composable
fun Router(mainModel: MainModel) {

    val navController = rememberNavController()
    val homeModel = hiltViewModel<HomeScreenViewModel>()
    val scaffoldState = rememberScaffoldState()


    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            if (!mainModel.state.showSplash){
                DefineBottomNav(
                    navController,
                )
            }
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = HOME.route,
            modifier = Modifier.padding(bottom =  it.calculateBottomPadding())
        ) {
            composable(HOME.route) {
                HomeScreen(homeModel, mainModel)
            }
        }
    }

}
