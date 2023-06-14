package com.example.paybackchallenge.usecases

import com.example.paybackchallenge.data.datasource.ImagesRepository
import com.example.paybackchallenge.domain.entities.Car
import com.example.paybackchallenge.domain.entities.Image
import com.example.paybackchallenge.domain.entities.SuperCharges
import kotlinx.coroutines.delay
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val imagesRepo: ImagesRepository) {

    suspend fun searchImages(text: String): List<Image> {
        return imagesRepo.searchImages(text)
    }
}

