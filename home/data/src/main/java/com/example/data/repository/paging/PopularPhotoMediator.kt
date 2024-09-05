package com.example.data.repository.paging

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.data.database.UnsplashDatabase
import com.example.data.database.models.PopularPhotoDB
import com.example.data.database.models.PopularPhotoRemoteKeys
import com.example.data.models.Order
import com.example.data.models.toPopularPhotoDB
import com.example.data.models.toUserDB
import com.example.data.service.HomeService

@OptIn(ExperimentalPagingApi::class)
class PopularPhotoMediator(
    private val homeService: HomeService,
    private val unsplashDatabase: UnsplashDatabase,
    private val popularPhotoError: (Exception) -> Unit,
) : RemoteMediator<Int, PopularPhotoDB>() {
    private val popularPhotoDao = unsplashDatabase.popularPhotoDao()
    private val popularPhotoRemoteKeyDao = unsplashDatabase.popularPhotoRemoteKeyDao()
    private val userDao = unsplashDatabase.userDao()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PopularPhotoDB>,
    ): MediatorResult {
        return try {
            val currentPage =
                when (loadType) {
                    LoadType.REFRESH -> {
                        val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                        remoteKeys?.nextPage?.minus(1) ?: 1
                    }
                    LoadType.PREPEND -> {
                        val remoteKeys = getRemoteKeyForFirstItem(state)

                        remoteKeys?.prevPage
                            ?: return MediatorResult.Success(
                                endOfPaginationReached = remoteKeys != null,
                            )
                    }
                    LoadType.APPEND -> {
                        val remoteKeys = getRemoteKeyForLastItem(state)

                        remoteKeys?.nextPage
                            ?: return MediatorResult.Success(
                                endOfPaginationReached = remoteKeys != null,
                            )
                    }
                }

            val response =
                try {
                    homeService.getPhotoList(
                        page = currentPage,
                        perPage = PagingConst.PAGE_SIZE,
                        orderBy = Order.POPULAR.order,
                    )
                } catch (e: Exception) {
                    popularPhotoError(e)
                    emptyList()
                }

            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            if (loadType == LoadType.REFRESH) {
                popularPhotoDao.deleteAllPopularPhoto()
                popularPhotoRemoteKeyDao.deleteAllPopularPhotoKey()
            }
            val keys =
                response.map { photo ->
                    PopularPhotoRemoteKeys(
                        id = photo.id,
                        prevPage = prevPage,
                        nextPage = nextPage,
                    )
                }

            popularPhotoRemoteKeyDao.addAllPopularPhotoKey(remoteKeys = keys)
            val users = response.map { it.user.toUserDB() }
            userDao.addAllUser(users)
            val photos = response.map { it.toPopularPhotoDB() }
            popularPhotoDao.addAllPopularPhoto(popularPhotoList = photos)
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            popularPhotoError(e)
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, PopularPhotoDB>): PopularPhotoRemoteKeys? =
        state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                popularPhotoRemoteKeyDao.getPopularPhotoKey(id = id)
            }
        }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PopularPhotoDB>): PopularPhotoRemoteKeys? =
        state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data
            ?.firstOrNull()
            ?.let { photoPresentation ->
                popularPhotoRemoteKeyDao.getPopularPhotoKey(id = photoPresentation.id)
            }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PopularPhotoDB>): PopularPhotoRemoteKeys? =
        state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data
            ?.lastOrNull()
            ?.let { photoPresentation ->
                popularPhotoRemoteKeyDao.getPopularPhotoKey(id = photoPresentation.id)
            }
}
