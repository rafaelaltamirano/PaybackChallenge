package com.example.paybackchallenge.framework.api

import com.example.paybackchallenge.domain.ResponseWrapper
import com.example.paybackchallenge.domain.response.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PixabayApi {
    @GET("api/")
    suspend fun searchImages(
        @Query("key") apiKey: String,
        @Query("q") search: String,
        @Query("image_type") image_type: String,
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): Response<ResponseWrapper<List<ImageResponse>>>
}