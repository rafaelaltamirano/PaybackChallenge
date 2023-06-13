package com.example.paybackchallenge.framework.di

import android.content.Context
import androidx.room.Room
import com.example.paybackchallenge.framework.database.AppDatabase
import com.example.paybackchallenge.data.dao.ImagesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "paybackDataBase"
        ).build()
    }

    @Provides
    fun provideBankDao(appDatabase: AppDatabase): ImagesDao {
        return appDatabase.imagesDao()
    }

}
