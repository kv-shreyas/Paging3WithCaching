package com.shreyasKv.paging3withcachingandcompose.data.remote

data class DrinkDto(
    val id:Int,
    val name:String,
    val tagline:String,
    val description:String,
    val first_brewed:String,
    val image_url: String?
)
