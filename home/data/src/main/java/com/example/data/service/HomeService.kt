package com.example.data.service

import com.example.data.models.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    @GET("photos")
    suspend fun getPhotoList(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("order_by") orderBy: String?,
        @Query("client_id") client: String = "oSC9x2PI9Zm3BrF6lQa4ClW5b9GfcQbibqndQArb76g",
    ): List<Photo>
}
