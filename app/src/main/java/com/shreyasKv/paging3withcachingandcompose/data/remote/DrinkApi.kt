package com.shreyasKv.paging3withcachingandcompose.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkApi {

    @GET("beers")
    suspend fun getBeer(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): List<DrinkDto>

    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/";
    }

}