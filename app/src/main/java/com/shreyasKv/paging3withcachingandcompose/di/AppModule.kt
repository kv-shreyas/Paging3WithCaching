package com.shreyasKv.paging3withcachingandcompose.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.shreyasKv.paging3withcachingandcompose.data.local.DrinkDatabase
import com.shreyasKv.paging3withcachingandcompose.data.local.DrinkEntity
import com.shreyasKv.paging3withcachingandcompose.data.remote.DrinkApi
import com.shreyasKv.paging3withcachingandcompose.data.remote.DrinkRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBeerDatabase(@ApplicationContext context: Context): DrinkDatabase {
        return Room.databaseBuilder(
            context,
            DrinkDatabase::class.java,
            "beers.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBeerApi():DrinkApi{
        return Retrofit.Builder()
            .baseUrl(DrinkApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideBeerPager(beerDatabase: DrinkDatabase, beerApi: DrinkApi):Pager<Int,DrinkEntity>{
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = DrinkRemoteMediator(
                beerDatabase = beerDatabase,
                beerApi =  beerApi),
            pagingSourceFactory = {beerDatabase.dao.pagingSource()})
    }
}