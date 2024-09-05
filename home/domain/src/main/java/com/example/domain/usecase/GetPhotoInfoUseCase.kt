package com.example.domain.usecase

import com.example.domain.repository.HomeRepository

class GetPhotoInfoUseCase(
    private val homeRepository: HomeRepository,
) {
    suspend fun getPhotoInfo(photoId: String) = homeRepository.getPhotoInfo(photoId)
}
