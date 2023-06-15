package com.example.paybackchallenge.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.paybackchallenge.domain.entities.Image

@Dao
interface ImagesDao {

    @Query("SELECT * FROM images")
    suspend fun getAll(): List<Image>

    @Query("SELECT * FROM images")
    fun pagingSource(): PagingSource<Int, Image>

    @Upsert
    suspend fun insertAll(banks: List<Image>)

    @Query("DELETE FROM images")
    suspend fun deleteAll()
}