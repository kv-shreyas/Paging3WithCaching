package com.shreyasKv.paging3withcachingandcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DrinkEntity::class],
    version = 1
)
abstract class DrinkDatabase :RoomDatabase(){
    abstract val dao: DrinkDao
}