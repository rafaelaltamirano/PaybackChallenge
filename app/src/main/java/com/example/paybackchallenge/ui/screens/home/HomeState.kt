package com.example.paybackchallenge.ui.screens.home

import com.example.paybackchallenge.domain.entities.Car
import com.example.paybackchallenge.domain.entities.Image
import com.example.paybackchallenge.domain.entities.SuperCharges


data class HomeState(
    val loading: Boolean = false,
    val imagesList: List<Image>? = emptyList(),
    val itemImage: Image? = null,
    val openDialog: Boolean = false
)