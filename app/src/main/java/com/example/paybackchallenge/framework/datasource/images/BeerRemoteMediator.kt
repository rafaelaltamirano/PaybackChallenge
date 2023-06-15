package com.example.paybackchallenge.framework.datasource.images

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.paybackchallenge.domain.entities.Image
import com.example.paybackchallenge.domain.exceptions.HttpException
import com.example.paybackchallenge.framework.api.PixabayApi
import com.example.paybackchallenge.framework.database.AppDatabase
import com.example.paybackchallenge.framework.di.API_KEY
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val db: AppDatabase,
    private val api: PixabayApi
): RemoteMediator<Int, Image>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Image>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val images = api.searchImages(
                API_KEY, "flowers", "photo",
                page = loadKey,
                pageCount = state.config.pageSize
            )
            val imageResult = images.body()?.hits?.map{ it.toEntity() } ?: emptyList()

            db.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    db.imagesDao().deleteAll()
                }
                db.imagesDao().insertAll(imageResult)
            }

            MediatorResult.Success(
                endOfPaginationReached = imageResult.isEmpty()
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}