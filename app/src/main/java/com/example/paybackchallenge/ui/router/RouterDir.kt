package com.example.paybackchallenge.ui.router

import com.example.paybackchallenge.R

enum class RouterDir (override val route: String, val icon: Int, val title: String) : Routers {
    SPLASH("splash",R.drawable.ic_car_solid,"splash"),
    HOME("home",R.drawable.ic_car_solid,"home"),
    SCREEN_1("screen_1", R.drawable.ic_search, "screen_1"),
    SCREEN_2("screen_2", R.drawable.ic_map_solid, "screen_2"),
    SCREEN_3("screen_3", R.drawable.ic_user_solid, "screen_3"),
}