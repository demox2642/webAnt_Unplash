package com.example.data.repository.paging

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.models.toPhotoPresentation
import com.example.data.service.HomeService
import com.example.domain.models.PhotoPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchPhotoPagingSource(
    private val searchText: String,
    private val homeService: HomeService,
    private val popularPhotoError: (Exception) -> Unit,
) : PagingSource<Int, PhotoPresentation>() {
    override fun getRefreshKey(state: PagingState<Int, PhotoPresentation>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoPresentation> =
        try {
            val pageNumber = params.key ?: 0

            val response =
                withContext(Dispatchers.IO) {
                    homeService
                        .searchImages(
                            query = searchText,
                            page = pageNumber,
                            perPage = PagingConst.PAGE_SIZE,
                        ).images
                        .map { it.toPhotoPresentation() }
                }

            val prevKey = if (pageNumber > 0) pageNumber - 1 else null

            val nextKey = if (response.isNotEmpty()) pageNumber + 1 else null
            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (e: Exception) {
            popularPhotoError(e)
            LoadResult.Error(e)
        }
}
