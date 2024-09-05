package com.example.domain.usecase

import com.example.domain.repository.HomeRepository

class GetPopularPhotoUseCase(
    private val homeRepository: HomeRepository,
) {
    suspend fun getPopularPhoto(popularPhotoError: (Exception) -> Unit) = homeRepository.getPopularPhoto(popularPhotoError)
}
