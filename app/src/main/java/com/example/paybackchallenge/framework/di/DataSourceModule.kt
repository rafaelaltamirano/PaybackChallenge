package com.example.paybackchallenge.framework.di

import com.example.paybackchallenge.data.datasource.ImagesLocalSource
import com.example.paybackchallenge.data.datasource.ImagesRemoteSource
import com.example.paybackchallenge.framework.datasource.images.ImagesLocalSourceImp
import com.example.paybackchallenge.framework.datasource.images.ImagesRemoteSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindImagesLocalSource(imp: ImagesLocalSourceImp): ImagesLocalSource

    @Binds
    @Singleton
    abstract fun bindImagesRemoteSource(imp: ImagesRemoteSourceImp): ImagesRemoteSource

}