package com.example.paybackchallenge.framework.api

import com.example.paybackchallenge.domain.exceptions.HttpException
import com.example.paybackchallenge.domain.exceptions.UnauthorizedException
import com.example.paybackchallenge.framework.schemas.ApiError
import com.google.gson.Gson
import retrofit2.Response

object ApiTools {
     //si la respuesta no es buena mapea el json de error
    fun <T>validateResponseOrFail(res: Response<T>) {
        if (res.isSuccessful) return
        val error = res.errorBody()!!.string()
        when (val code = res.code()) {
            401 -> throw UnauthorizedException()
            else -> {
                val errorMessage = try {
                    Gson().fromJson(error, ApiError::class.java).toEntity()
                } catch (e: Exception) {
                    res.message()
                }
                throw HttpException(code, errorMessage)
            }
        }
    }

}