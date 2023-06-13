package com.example.paybackchallenge.domain.entities

data class SuperCharges (
    val address: String,
    val available: Int,
    val total: Int,
    val kms: Float,
)