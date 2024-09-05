package com.example.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.models.PhotoPresentation
import com.example.domain.usecase.GetNewPhotoUseCase
import com.example.domain.usecase.GetPopularPhotoUseCase
import com.example.presentation.view.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeMainViewModel
    @Inject
    constructor(
        private val getNewPhotoUseCase: GetNewPhotoUseCase,
        private val getPopularPhotoUseCase: GetPopularPhotoUseCase,
    ) : ViewModel() {
        private val _newPhotoScreenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
        val newPhotoScreenState = _newPhotoScreenState.asStateFlow()

        private val _popularPhotoScreenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
        val popularPhotoScreenState = _popularPhotoScreenState.asStateFlow()

        private val _newPhotoList = MutableStateFlow<PagingData<PhotoPresentation>>(PagingData.empty())
        val newPhotoList = _newPhotoList.asStateFlow()

        private val _popularPhotoList = MutableStateFlow<PagingData<PhotoPresentation>>(PagingData.empty())
        val popularPhotoList = _popularPhotoList.asStateFlow()

        private val _errorState = MutableStateFlow<Exception?>(null)
        val errorState = _errorState.asStateFlow()

        init {
            getPopularPhoto()
            getNewPhoto()
        }

        fun getPopularPhoto() {
            viewModelScope.launch(Dispatchers.IO) {
                _popularPhotoScreenState.value = ScreenState.Loading
                getPopularPhotoUseCase
                    .getPopularPhoto {
                        _errorState.value = it
                    }.cachedIn(viewModelScope)
                    .collect {
                        _popularPhotoList.value = it
                        _popularPhotoScreenState.value = ScreenState.Success
                    }
            }
        }

        fun getNewPhoto() {
            viewModelScope.launch(Dispatchers.IO) {
                _newPhotoScreenState.value = ScreenState.Loading
                getNewPhotoUseCase
                    .getNewPhoto {
                        _errorState.value = it
                    }.cachedIn(viewModelScope)
                    .collect {
                        _newPhotoList.value = it
                        _newPhotoScreenState.value = ScreenState.Success
                    }
            }
        }

        fun cleanError() {
            _errorState.value = null
        }
    }
