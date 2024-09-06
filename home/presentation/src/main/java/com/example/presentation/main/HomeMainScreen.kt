package com.example.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.domain.models.PhotoPresentation
import com.example.domain.models.PhotoTableName
import com.example.home.presentation.R
import com.example.presentation.HomeScreens
import com.example.presentation.theme.AppTheme
import com.example.presentation.view.AlertDialog
import com.example.presentation.view.ScreenState
import com.example.presentation.view.TabPager
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeMainScreen(navController: NavHostController) {
    val viewModel: HomeMainViewModel = hiltViewModel()
    val popularPhotoList = viewModel.popularPhotoList.collectAsLazyPagingItems()
    val newPhotoList = viewModel.newPhotoList.collectAsLazyPagingItems()
    val newPhotoScreenState by viewModel.newPhotoScreenState.collectAsState()
    val popularPhotoScreenState by viewModel.popularPhotoScreenState.collectAsState()
    val errorState by viewModel.errorState.collectAsState()

    HomeMainContent(
        popularPhotoList = popularPhotoList,
        newPhotoList = newPhotoList,
        updateNewPhoto = viewModel::getNewPhoto,
        updatePopularPhoto = viewModel::getNewPhoto,
        newPhotoScreenState = newPhotoScreenState,
        popularPhotoScreenState = popularPhotoScreenState,
        errorState = errorState,
        closeErrorDialog = viewModel::cleanError,
        toDetail = { id, table ->
            navController.navigate(HomeScreens.HomeDetailScreen.route + "/$id" + "/$table")
        },
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeMainContent(
    popularPhotoList: LazyPagingItems<PhotoPresentation>,
    newPhotoList: LazyPagingItems<PhotoPresentation>,
    updateNewPhoto: () -> Unit,
    updatePopularPhoto: () -> Unit,
    newPhotoScreenState: ScreenState,
    popularPhotoScreenState: ScreenState,
    errorState: Exception?,
    closeErrorDialog: () -> Unit,
    toDetail: (String, PhotoTableName) -> Unit,
) {
    val loading = newPhotoScreenState == ScreenState.Loading
    Scaffold { paging ->
        Column {
            val searchText = remember { mutableStateOf("") }
            if (errorState != null) {
                AlertDialog(message = errorState.message.toString(), closeDialog = closeErrorDialog)
            }

            OutlinedTextField(
                modifier =
                    Modifier
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                        .fillMaxWidth(),
                value = searchText.value,
                maxLines = 1,
                leadingIcon = {
                    Icon(
                        modifier =
                            Modifier
                                .padding(start = 10.dp)
                                .size(20.dp),
                        painter = painterResource(id = com.example.base.presentation.R.drawable.ic_search),
                        contentDescription = "",
                        tint = Color(0xFF7A7A7E),
                    )
                },
                onValueChange = { searchText.value = it },
                enabled = false,
                shape = RoundedCornerShape(28.dp),
                placeholder = {
                    Text(
                        modifier = Modifier.padding(top = 3.dp),
                        text = stringResource(id = R.string.search),
                        style = AppTheme.typography.h3,
                        color = Color(0xFF7A7A7E),
                    )
                },
                textStyle = AppTheme.typography.body1,
                colors =
                    OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = AppTheme.colors.white,
                        disabledBorderColor = AppTheme.colors.white,
                        disabledContainerColor = AppTheme.colors.grayLight,
                        focusedContainerColor = AppTheme.colors.grayLight,
                        unfocusedContainerColor = AppTheme.colors.grayLight,
                    ),
            )

            TabPager(
                modifier =
                    Modifier
                        .padding(paging)
                        .padding(horizontal = 16.dp),
                tabs =
                    listOf(
                        stringResource(R.string.tab_new) to 0,
                        stringResource(R.string.tab_popular) to 1,
                    ),
                loading = loading,
            ) {
                when (it) {
                    0 ->
                        NewPhotoContent(
                            newPhotoList = newPhotoList,
                            updateNewPhoto = updateNewPhoto,
                            newPhotoScreenState = newPhotoScreenState,
                            toDetail = toDetail,
                        )

                    1 ->
                        PopularPhotoContent(
                            popularPhotoList = popularPhotoList,
                            updatePopularPhoto = updatePopularPhoto,
                            popularPhotoScreenState = popularPhotoScreenState,
                            toDetail = toDetail,
                        )
                }
            }
        }
    }
}
