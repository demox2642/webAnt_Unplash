package com.example.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.PhotoDetail
import com.example.domain.models.PhotoTableName
import com.example.domain.usecase.GetPhotoInfoUseCase
import com.example.presentation.view.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeDetailViewModel
    @Inject
    constructor(
        private val getPhotoInfoUseCase: GetPhotoInfoUseCase,
    ) : ViewModel() {
        private val _photoDetailScreenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
        val photoDetailScreenState = _photoDetailScreenState.asStateFlow()

        private val _photoDetail = MutableStateFlow<PhotoDetail?>(null)
        val photoDetail = _photoDetail.asStateFlow()

        fun getPhotoDetail(
            photoId: String,
            tableName: PhotoTableName,
        ) {
            viewModelScope.launch(Dispatchers.IO) {
                getPhotoInfoUseCase.getPhotoInfo(photoId, tableName).collect {
                    _photoDetail.value = it
                    _photoDetailScreenState.value = ScreenState.Success
                }
            }
        }
    }
