package com.example.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.domain.models.PhotoDetail
import com.example.domain.models.PhotoTableName
import com.example.home.presentation.R
import com.example.presentation.theme.AppTheme
import com.example.presentation.view.ScreenState
import com.example.presentation.view.UnsplushProgressCircular
import java.time.format.DateTimeFormatter

@Composable
fun HomeDetailScreen(
    navController: NavHostController,
    photoId: String,
    tableName: String,
) {
    val viewModel: HomeDetailViewModel = hiltViewModel()
    val table = PhotoTableName.valueOf(tableName)
    viewModel.getPhotoDetail(photoId, table)

    val photoDetailScreenState by viewModel.photoDetailScreenState.collectAsState()
    val photoDetail by viewModel.photoDetail.collectAsState()

    HomeDetailContent(photoDetailScreenState = photoDetailScreenState, photoDetail = photoDetail, back = { navController.popBackStack() })
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeDetailContent(
    photoDetailScreenState: ScreenState,
    photoDetail: PhotoDetail?,
    back: () -> Unit,
) {
    Scaffold(
        topBar = {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                IconButton(onClick = { back() }) {
                    Icon(
                        painter = painterResource(id = com.example.base.presentation.R.drawable.ic_arrow_left),
                        contentDescription = "back",
                        tint = AppTheme.colors.gray,
                    )
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = com.example.base.presentation.R.drawable.ic_dots3),
                        contentDescription = "dots",
                        tint = AppTheme.colors.black,
                    )
                }
            }
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            when (photoDetailScreenState) {
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
                    photoDetail?.let {
                        GlideImage(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .height(240.dp),
                            model = photoDetail.url,
                            contentScale = ContentScale.Crop,
                            contentDescription = "",
                            loading = placeholder(ColorPainter(AppTheme.colors.grayLight)),
                            transition = CrossFade,
                        ) {
                            it
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                        }
                        Column(modifier = Modifier.padding(16.dp)) {
                            if (photoDetail.name.isNullOrEmpty()) {
                                Text(text = "Image Name", style = AppTheme.typography.h2, color = AppTheme.colors.black)
                            } else {
                                Text(text = photoDetail.name!!, style = AppTheme.typography.h2, color = AppTheme.colors.black)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .height(22.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Text(text = photoDetail.username, style = AppTheme.typography.body1, color = AppTheme.colors.gray)
                                Text(
                                    text = photoDetail.createDataTime.format(DateTimeFormatter.ofPattern("dd.mm.yyyy")),
                                    style = AppTheme.typography.body1,
                                    color = AppTheme.colors.gray,
                                )
                            }
                            Spacer(modifier = Modifier.height(14.dp))
                            if (photoDetail.description.isNullOrEmpty()) {
                                Text(
                                    text = stringResource(id = com.example.base.presentation.R.string.lorem),
                                    style = AppTheme.typography.body1,
                                    color = AppTheme.colors.black,
                                )
                            } else {
                                Text(
                                    text = photoDetail.description!!,
                                    style = AppTheme.typography.body1,
                                    color = AppTheme.colors.black,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
