package com.example.paybackchallenge.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.paybackchallenge.data.dao.ImagesDao
import com.example.paybackchallenge.domain.entities.Image

@Database(entities = [Image::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imagesDao(): ImagesDao
}