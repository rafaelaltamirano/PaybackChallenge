package com.example.paybackchallenge.usecases

import com.example.paybackchallenge.data.datasource.ImagesRepository
import com.example.paybackchallenge.domain.entities.User
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainUseCase @Inject constructor(private val imagesRepo: ImagesRepository) {

    suspend fun loadImagesFromCache() = imagesRepo.load()
}