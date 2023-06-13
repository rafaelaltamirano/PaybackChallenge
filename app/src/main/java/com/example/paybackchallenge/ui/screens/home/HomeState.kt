package com.example.paybackchallenge.ui.screens.home

import com.example.paybackchallenge.domain.entities.Car
import com.example.paybackchallenge.domain.entities.Image
import com.example.paybackchallenge.domain.entities.SuperCharges


data class HomeState(
    val car: Car? = null,
    val loading: Boolean = false,
    val listSuperCharges: List<SuperCharges> = emptyList(),
    val imagesList: List<Image> = emptyList()
)