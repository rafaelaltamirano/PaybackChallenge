package com.example.paybackchallenge.framework.di

import com.example.paybackchallenge.framework.api.PixabayApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun providesPixabayApi(client: OkHttpClient): PixabayApi {
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
        client.also(builder::client)
        return builder.build().create(PixabayApi::class.java)
    }
}