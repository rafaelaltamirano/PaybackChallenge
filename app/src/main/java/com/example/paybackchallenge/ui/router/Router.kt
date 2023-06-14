package com.example.paybackchallenge.ui.router

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.paybackchallenge.ui.main.MainViewModel
import com.example.paybackchallenge.ui.router.RouterDir.*
import com.example.paybackchallenge.ui.screens.details.DetailScreen
import com.example.paybackchallenge.ui.screens.home.HomeScreen
import com.example.paybackchallenge.ui.screens.home.HomeViewModel
import com.example.paybackchallenge.ui.splash.SplashScreen

@Composable
fun Router(mainModel: MainViewModel) {

    val navController = rememberNavController()
    val homeModel = hiltViewModel<HomeViewModel>()


    NavHost(
        navController = navController,
        startDestination = if (mainModel.state.showSplash) SPLASH.route else HOME.route,
    ) {
        composable(SPLASH.route) { SplashScreen() }
        composable(HOME.route) {
            HomeScreen(homeModel, mainModel, navController)
        }
        composable(DETAILS.route) {
            DetailScreen(
                itemImage = homeModel.state.itemImage,
                onBack = { navController.popBackStack() })
        }
    }

}
