package com.example.data.service

import com.example.data.models.Photo
import com.example.data.models.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeService {
    @GET("photos")
    suspend fun getPhotoList(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("order_by") orderBy: String?,
        @Query("client_id") client: String = "oSC9x2PI9Zm3BrF6lQa4ClW5b9GfcQbibqndQArb76g",
    ): Response<List<Photo>>

    @GET("search/photos")
    suspend fun searchImages(
        @Query("query") query: String,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int,
        @Query("client_id") client: String = "oSC9x2PI9Zm3BrF6lQa4ClW5b9GfcQbibqndQArb76g",
    ): SearchResult

    @GET("photos/{id}")
    suspend fun getPhotoInfo(
        @Path("id") id: String,
        @Query("client_id") client: String = "oSC9x2PI9Zm3BrF6lQa4ClW5b9GfcQbibqndQArb76g",
    ): Photo
}
