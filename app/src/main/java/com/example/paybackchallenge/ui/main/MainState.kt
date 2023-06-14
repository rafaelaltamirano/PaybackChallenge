package com.example.paybackchallenge.ui.main

import com.example.paybackchallenge.domain.entities.User

data class MainState(
    val loading: Boolean = false,
    val showSplash : Boolean = true
)