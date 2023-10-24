package com.shreyasKv.paging3withcachingandcompose.domain

data class Drink(
    val id:Int,
    val name:String,
    val tagLine:String,
    val description:String,
    val firstBrewed:String,
    val imageUrl: String?
)
