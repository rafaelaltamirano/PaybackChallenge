package com.example.paybackchallenge.domain

data class ResponseWrapper<T>(
    val total: Int,
    val totalHits: Int,
    val hits: T
)