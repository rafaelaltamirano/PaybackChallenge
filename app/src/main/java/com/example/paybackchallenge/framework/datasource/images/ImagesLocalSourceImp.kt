package com.example.paybackchallenge.framework.datasource.images

import com.example.paybackchallenge.data.dao.ImagesDao
import com.example.paybackchallenge.data.datasource.ImagesLocalSource
import com.example.paybackchallenge.domain.entities.Image
import javax.inject.Inject


class ImagesLocalSourceImp @Inject constructor(private val imagesDao: ImagesDao) :
    ImagesLocalSource {

    override suspend fun save(images: List<Image>) {

        imagesDao.insertAll(images)
    }

    override suspend fun load(): List<Image>? {
        return imagesDao.getAll()
    }

    override suspend fun clear() {
        return imagesDao.deleteAll()
    }

}