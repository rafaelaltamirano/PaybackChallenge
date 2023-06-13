package com.example.paybackchallenge.framework.datasource.images

import android.content.Context
import com.example.paybackchallenge.data.datasource.ImagesRemoteSource
import com.example.paybackchallenge.domain.entities.Image
import com.example.paybackchallenge.framework.api.ApiTools
import com.example.paybackchallenge.framework.api.PixabayApi
import com.example.paybackchallenge.framework.di.API_KEY
import javax.inject.Inject


class ImagesRemoteSourceImp @Inject constructor(
    private val api: PixabayApi
) : ImagesRemoteSource {

    override suspend fun searchImages(): List<Image> {
        val res = api.searchImages(API_KEY,"fruit","photo")
        ApiTools.validateResponseOrFail(res)
        return res.body()!!.hits.map { it.toEntity() }
    }
}