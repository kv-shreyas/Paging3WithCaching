package com.shreyasKv.paging3withcachingandcompose.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.shreyasKv.paging3withcachingandcompose.data.local.DrinkDatabase
import com.shreyasKv.paging3withcachingandcompose.data.local.DrinkEntity
import com.shreyasKv.paging3withcachingandcompose.data.mappers.toDrinkEntity
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class DrinkRemoteMediator(
    private val beerDatabase: DrinkDatabase, private val beerApi: DrinkApi
) : RemoteMediator<Int, DrinkEntity>() {

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, DrinkEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }
//            delay(2000L)
            val beers = beerApi.getBeer(
                page = loadKey, pageCount = state.config.pageSize
            )
            beerDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    beerDatabase.dao.clearALl()
                }
                val beerEntities = beers.map { it.toDrinkEntity() }
                beerDatabase.dao.upsert(beerEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = beers.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }

    }

}