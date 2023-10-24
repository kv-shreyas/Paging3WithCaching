package com.shreyasKv.paging3withcachingandcompose.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beerentity")
data class DrinkEntity(
    @PrimaryKey
    val id:Int,
    val name:String,
    val tagline:String,
    val description:String,
    val firstBrewed:String,
    val imageUrl: String?
)