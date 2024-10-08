package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.models.PhotoDetail
import com.example.domain.models.PhotoPresentation
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getPopularPhoto(popularPhotoError: (Exception) -> Unit): Flow<PagingData<PhotoPresentation>>

    suspend fun getNewPhoto(newPhotoError: (Exception) -> Unit): Flow<PagingData<PhotoPresentation>>

    suspend fun getNewPhotoInfo(photoId: String): Flow<PhotoDetail>

    suspend fun getPopularPhotoInfo(photoId: String): Flow<PhotoDetail>

    suspend fun getSearchPhotoInfo(photoId: String): Flow<PhotoDetail>

    suspend fun searchPhotos(
        searchText: String,
        searchError: (Exception) -> Unit,
    ): Flow<PagingData<PhotoPresentation>>
}
