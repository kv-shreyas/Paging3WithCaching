package com.shreyasKv.paging3withcachingandcompose.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface DrinkDao {
    @Upsert
    suspend fun upsert(beers: List<DrinkEntity>)

    @Query("SELECT * FROM beerentity")
    fun pagingSource():PagingSource<Int,DrinkEntity>

    @Query("DELETE FROM beerentity")
    suspend fun clearALl()
}