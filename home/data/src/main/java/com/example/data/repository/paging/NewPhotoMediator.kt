@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.example.data.repository.paging

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.data.database.UnsplashDatabase
import com.example.data.database.models.NewPhotoDB
import com.example.data.database.models.NewPhotoRemoteKeys
import com.example.data.models.Order
import com.example.data.models.toPhotoDB
import com.example.data.models.toUserDB
import com.example.data.service.HomeService

@ExperimentalPagingApi
class NewPhotoMediator(
    private val homeService: HomeService,
    private val unsplashDatabase: UnsplashDatabase,
    private val newPhotoError: (Exception) -> Unit,
) : RemoteMediator<Int, NewPhotoDB>() {
    private val newPhotoDao = unsplashDatabase.newPhotoDao()
    private val newPhotoRemoteKeysDao = unsplashDatabase.newPhotoRemoteKeyDao()
    private val userDao = unsplashDatabase.userDao()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NewPhotoDB>,
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
                        orderBy = Order.LATEST.order,
                    )
                } catch (e: Exception) {
                    newPhotoError(e)
                    emptyList()
                }

            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            if (loadType == LoadType.REFRESH) {
                newPhotoDao.deleteNewPhoto()
                newPhotoRemoteKeysDao.deleteAllNewPhotoRemote()
            }
            val keys =
                response.map { photo ->
                    NewPhotoRemoteKeys(
                        id = photo.id,
                        prevPage = prevPage,
                        nextPage = nextPage,
                    )
                }
            newPhotoRemoteKeysDao.addAllNewPhotoRemote(remoteKeys = keys)
            val users = response.map { it.user.toUserDB() }
            userDao.addAllUser(users)
            val photos = response.map { it.toPhotoDB() }
            newPhotoDao.addAllNewPhoto(newPhotoList = photos)
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            newPhotoError(e)
            return MediatorResult.Error(e)
        }
    }

    private fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, NewPhotoDB>): NewPhotoRemoteKeys? =
        state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                newPhotoRemoteKeysDao.getNewPhotoRemote(id = id)
            }
        }

    private fun getRemoteKeyForFirstItem(state: PagingState<Int, NewPhotoDB>): NewPhotoRemoteKeys? =
        state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data
            ?.firstOrNull()
            ?.let { photoPresentation ->
                newPhotoRemoteKeysDao.getNewPhotoRemote(id = photoPresentation.id)
            }

    private fun getRemoteKeyForLastItem(state: PagingState<Int, NewPhotoDB>): NewPhotoRemoteKeys? =
        state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data
            ?.lastOrNull()
            ?.let { photoPresentation ->
                newPhotoRemoteKeysDao.getNewPhotoRemote(id = photoPresentation.id)
            }
}
