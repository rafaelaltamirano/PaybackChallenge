package com.example.paybackchallenge.usecases

import com.example.paybackchallenge.data.dao.UserDao
import com.example.paybackchallenge.domain.entities.User
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainUseCase @Inject constructor(private val userDao: UserDao) {

    suspend fun getUser(): User {
        delay(3000)
        return userDao.getUser()
    }
}