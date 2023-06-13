package com.example.paybackchallenge.domain

interface Response<T> {

    fun toEntity(): T

}