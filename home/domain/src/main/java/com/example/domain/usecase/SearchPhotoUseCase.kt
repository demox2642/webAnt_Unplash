package com.example.domain.usecase

import com.example.domain.repository.HomeRepository

class SearchPhotoUseCase(
    private val repository: HomeRepository,
) {
    suspend fun searchPhoto(
        searchText: String,
        searchError: (Exception) -> Unit,
    ) = repository.searchPhotos(searchText, searchError)
}
