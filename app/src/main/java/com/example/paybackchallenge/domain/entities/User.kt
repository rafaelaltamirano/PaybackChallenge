package com.example.paybackchallenge.domain.entities

data class User(
    val id: String? = null,
    val email: String,
    val password: String?,
    val firstName: String,
    val lastName: String,
    val address: String,
)
