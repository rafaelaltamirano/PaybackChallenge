package com.example.paybackchallenge.framework.datasource.images

import com.example.paybackchallenge.data.datasource.ImagesRemoteSource
import com.example.paybackchallenge.domain.entities.Image
import com.example.paybackchallenge.framework.api.ApiTools
import com.example.paybackchallenge.framework.api.PixabayApi
import com.example.paybackchallenge.framework.di.API_KEY
import javax.inject.Inject


class ImagesRemoteSourceImp @Inject constructor(
    private val api: PixabayApi
) : ImagesRemoteSource {

    override suspend fun searchImages(text: String): List<Image> {
        val res = api.searchImages(API_KEY,text,"photo")
        ApiTools.validateResponseOrFail(res)
        return res.body()!!.hits.map { it.toEntity() }
    }
}