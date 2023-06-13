package com.example.paybackchallenge.domain.entities

import com.example.paybackchallenge.domain.enum.Escale

data class Statistics (
    val scale: Escale,
    val quantity: String,
)