package com.example.paybackchallenge.data.dao

import com.example.paybackchallenge.domain.entities.Car
import com.example.paybackchallenge.domain.entities.SuperCharges
import com.example.paybackchallenge.domain.entities.User

interface UserDao {
    suspend fun getUser(): User
    suspend fun getCar(): Car
    suspend fun getSuperCharges(): List<SuperCharges>
}