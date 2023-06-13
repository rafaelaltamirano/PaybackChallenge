package com.example.paybackchallenge.usecases

import com.example.paybackchallenge.data.dao.UserDao
import com.example.paybackchallenge.data.datasource.ImagesRepository
import com.example.paybackchallenge.domain.entities.Car
import com.example.paybackchallenge.domain.entities.Image
import com.example.paybackchallenge.domain.entities.SuperCharges
import kotlinx.coroutines.delay
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val userDao: UserDao,private val imagesRepo: ImagesRepository) {

    suspend fun getCar(): Car {
        delay(3000)
        return userDao.getCar()
    }

    suspend fun getSuperCharges(): List<SuperCharges> {
        delay(3000)
        return userDao.getSuperCharges()
    }

    suspend fun searchImages(): List<Image> {
        return imagesRepo.searchImages()
    }
}

