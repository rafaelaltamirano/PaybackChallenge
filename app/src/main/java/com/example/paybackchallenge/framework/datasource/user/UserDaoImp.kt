package com.example.paybackchallenge.framework.datasource.user

import com.example.paybackchallenge.data.dao.UserDao
import com.example.paybackchallenge.domain.entities.Car
import com.example.paybackchallenge.domain.entities.SuperCharges
import com.example.paybackchallenge.domain.entities.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject


class UserDaoImp @Inject constructor(
) : UserDao {

    override suspend fun getUser(): User {
        val json =
            "{\"id\":\"123456\",\"email\":\"example@example.com\",\"password\":\"mypassword\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"address\":\"123 Street\"}"
        val gson = Gson()
        return gson.fromJson(json, User::class.java)
    }

    override suspend fun getCar(): Car {
        val json =
            "{\"carModel\":\"Tesla model X\",\"statistics\":[{\"scale\":\"VOLTAGE\",\"quantity\":\"240\"},{\"scale\":\"REMAINING_CHARGE\",\"quantity\":\"540\"},{\"scale\":\"FAST_CHARGE\",\"quantity\":\"20\"}]}"
        val gson = Gson()
        return gson.fromJson(json, Car::class.java)
    }


    override suspend fun getSuperCharges(): List<SuperCharges> {
        val json = """
[
  {"address": "Dirección 1", "available": 5, "total": 10, "kms": 120.5},
  {"address": "Dirección 2", "available": 3, "total": 8, "kms": 95.2},
  {"address": "Dirección 3", "available": 2, "total": 5, "kms": 70.7},
  {"address": "Dirección 4", "available": 7, "total": 12, "kms": 150.0},
  {"address": "Dirección 5", "available": 1, "total": 3, "kms": 50.5},
  {"address": "Dirección 6", "available": 4, "total": 6, "kms": 80.2},
  {"address": "Dirección 7", "available": 6, "total": 9, "kms": 105.8},
  {"address": "Dirección 8", "available": 0, "total": 2, "kms": 30.3},
  {"address": "Dirección 9", "available": 9, "total": 10, "kms": 115.9},
  {"address": "Dirección 10", "available": 2, "total": 4, "kms": 60.0}
]
"""
        val gson = Gson()
        val listType = object : TypeToken<List<SuperCharges>>() {}.type
        return gson.fromJson(json, listType)
    }
}