package com.example.paybackchallenge.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paybackchallenge.domain.entities.Image

@Dao
interface ImagesDao {

    @Query("SELECT * FROM images")
    suspend fun getAll(): List<Image>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(banks: List<Image>)

    @Query("DELETE FROM images")
    suspend fun deleteAll()
}