package com.example.paybackchallenge.framework.schemas

import com.example.paybackchallenge.domain.Response

data class ApiError(
    val success: String,
    val message: String,
    val status_code: String,
    val data: Data,
) : Response<String> {

    data class Data(
        val resultDescription: String,
        val resultError: List<String>,
        val resultDate: String
    )

    override fun toEntity() = data.resultDescription

}
