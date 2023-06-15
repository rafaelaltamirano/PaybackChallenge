package com.example.paybackchallenge.framework.di

import com.example.paybackchallenge.data.datasource.ImagesLocalSource
import com.example.paybackchallenge.data.datasource.ImagesRemoteSource
import com.example.paybackchallenge.data.datasource.ImagesRepository
import com.example.paybackchallenge.data.datasource.LocalSource
import com.example.paybackchallenge.domain.entities.Image
import com.example.paybackchallenge.framework.api.PixabayApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providerImagesRepository(
        remote: ImagesRemoteSource,
        local: ImagesLocalSource,
    api : PixabayApi): ImagesRepository {
        return ImagesRepository(remote,local,api)
    }

}