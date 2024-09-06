package com.example.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.database.UnsplashDatabase
import com.example.data.ext.toDateTime
import com.example.data.models.toPhotoDetail
import com.example.data.repository.paging.NewPhotoMediator
import com.example.data.repository.paging.PagingConst.PAGE_SIZE
import com.example.data.repository.paging.PopularPhotoMediator
import com.example.data.service.HomeService
import com.example.domain.models.PhotoDetail
import com.example.domain.models.PhotoPresentation
import com.example.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class HomeRepositoryImpl
    @Inject
    constructor(
        private val homeService: HomeService,
        private val unsplashDatabase: UnsplashDatabase,
    ) : HomeRepository {
        @RequiresApi(Build.VERSION_CODES.O)
        override suspend fun getPopularPhoto(popularPhotoError: (Exception) -> Unit): Flow<PagingData<PhotoPresentation>> =
            Pager(
                config =
                    PagingConfig(
                        pageSize = PAGE_SIZE,
                        enablePlaceholders = false,
                    ),
                remoteMediator = PopularPhotoMediator(homeService, unsplashDatabase, popularPhotoError),
            ) { unsplashDatabase.popularPhotoDao().getAllImages() }
                .flow
                .map { pagingData ->
                    pagingData.map {
                        PhotoPresentation(
                            id = it.id,
                            url = it.url,
                            createDataTime = it.createDataTime.toDateTime(),
                            description = it.description,
                        )
                    }
                }.flowOn(Dispatchers.IO)

        @RequiresApi(Build.VERSION_CODES.O)
        override suspend fun getNewPhoto(newPhotoError: (Exception) -> Unit): Flow<PagingData<PhotoPresentation>> =
            Pager(
                config =
                    PagingConfig(
                        pageSize = PAGE_SIZE,
                        enablePlaceholders = false,
                    ),
                remoteMediator = NewPhotoMediator(homeService, unsplashDatabase, newPhotoError),
            ) {
                Log.e("HomeRepositoryImpl", "Load from DB")
                unsplashDatabase.newPhotoDao().getAllImages()
            }.flow
                .map { pagingData ->
                    pagingData.map {
                        PhotoPresentation(
                            id = it.id,
                            url = it.url,
                            createDataTime = it.createDataTime.toDateTime(),
                            description = it.description,
                        )
                    }
                }.flowOn(Dispatchers.IO)

        override suspend fun getNewPhotoInfo(photoId: String): Flow<PhotoDetail> =
            flow {
                emit(unsplashDatabase.newPhotoDao().getNewPhoto(photoId).toPhotoDetail())
            }.flowOn(Dispatchers.IO)

        override suspend fun getPoularPhotoInfo(photoId: String): Flow<PhotoDetail> =
            flow {
                emit(unsplashDatabase.popularPhotoDao().getPopularPhoto(photoId).toPhotoDetail())
            }.flowOn(Dispatchers.IO)
    }
