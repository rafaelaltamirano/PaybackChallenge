package com.example.paybackchallenge.framework.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.paybackchallenge.framework.database.AppDatabase
import com.example.paybackchallenge.data.dao.ImagesDao
import com.example.paybackchallenge.domain.entities.Image
import com.example.paybackchallenge.framework.api.PixabayApi
import com.example.paybackchallenge.framework.datasource.images.BeerRemoteMediator
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
    fun provideImagesDao(appDatabase: AppDatabase): ImagesDao {
        return appDatabase.imagesDao()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideImagePager(db: AppDatabase, api: PixabayApi): Pager<Int, Image> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = BeerRemoteMediator(
                db = db,
                api = api
            ),
            pagingSourceFactory = {
                db.imagesDao().pagingSource()
            }
        )
    }

}
