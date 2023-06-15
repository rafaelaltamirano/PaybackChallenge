package com.example.paybackchallenge.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.paybackchallenge.domain.entities.Image
import com.example.paybackchallenge.framework.api.PixabayApi
import com.example.paybackchallenge.framework.datasource.images.NewsPagingSource

class ImagesRepository(
    private val remote: ImagesRemoteSource,
    private val local: ImagesLocalSource,
    private val api: PixabayApi
) : LocalSource<List<Image>> {

    var images: List<Image>? = emptyList()
        private set

    fun getImages() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            NewsPagingSource(api)
        }
    ).flow
    suspend fun searchImages(text: String) = remote.searchImages(text).also { save(it) }

    override suspend fun save(t: List<Image>) {
        images = t
        local.save(t)
    }


    override suspend fun load(): List<Image> = (local.load() ?: emptyList()).also {
        this.images = it
    }

    override suspend fun clear() = local.clear().also { this.images = emptyList() }

}

interface ImagesRemoteSource {

    suspend fun searchImages(text: String): List<Image>

}

interface ImagesLocalSource : LocalSource<List<Image>>