package com.example.domain.usecase

import com.example.domain.repository.HomeRepository

class GetNewPhotoUseCase(
    private val homeRepository: HomeRepository,
) {
    suspend fun getNewPhoto(newPhotoError: (Exception) -> Unit) = homeRepository.getNewPhoto(newPhotoError)
}
