package com.example.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.domain.models.PhotoPresentation
import com.example.presentation.theme.AppTheme
import com.example.presentation.view.ErrorView
import com.example.presentation.view.ScreenState
import com.example.presentation.view.UnsplushProgressCircular
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PopularPhotoContent(
    popularPhotoList: LazyPagingItems<PhotoPresentation>,
    updatePopularPhoto: () -> Unit,
    popularPhotoScreenState: ScreenState,
) {
    SwipeRefresh(
        modifier = Modifier.fillMaxSize(),
        state = SwipeRefreshState(false),
        onRefresh = updatePopularPhoto,
    ) {
        when (popularPhotoScreenState) {
            ScreenState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    UnsplushProgressCircular()
                }
            }

            ScreenState.Success -> {
                if (popularPhotoList.itemCount == 0) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        ErrorView()
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(15.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        items(popularPhotoList.itemCount) { index ->
                            popularPhotoList[index]?.let { photo ->
                                GlideImage(
                                    modifier =
                                        Modifier
                                            .clip(RoundedCornerShape(10.dp))
                                            .size(156.dp),
                                    model = photo.url,
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "",
                                    loading = placeholder(ColorPainter(AppTheme.colors.grayLight)),
                                    transition = CrossFade,
                                ) {
                                    it
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
