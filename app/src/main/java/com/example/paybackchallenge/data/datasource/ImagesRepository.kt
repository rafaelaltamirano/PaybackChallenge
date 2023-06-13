package com.example.paybackchallenge.data.datasource

import com.example.paybackchallenge.domain.entities.Image

class ImagesRepository(
    private val remote: ImagesRemoteSource,
    private val local: ImagesLocalSource
) : LocalSource<List<Image>> {

    var images: List<Image>? = emptyList()
        private set

    suspend fun searchImages() = remote.searchImages().also { save(it) }

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

    suspend fun searchImages() : List<Image>

}

interface ImagesLocalSource : LocalSource<List<Image>>