package com.example.paybackchallenge.framework.datasource.images

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paybackchallenge.domain.entities.Image
import com.example.paybackchallenge.framework.api.PixabayApi
import com.example.paybackchallenge.framework.di.API_KEY
import javax.inject.Inject


class NewsPagingSource @Inject constructor(
    private val api: PixabayApi
) : PagingSource<Int, Image>() {
    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        return try {
            val page = params.key ?: 1
            val response = api.searchImages(API_KEY, "flowers", "photo", page = page,1)

            LoadResult.Page(
                data = response.body()!!.hits.map { it.toEntity() },
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.body()!!.hits.map { it.toEntity() }.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}