package com.example.domain.usecase

import com.example.domain.models.PhotoDetail
import com.example.domain.models.PhotoTableName
import com.example.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetPhotoInfoUseCase(
    private val homeRepository: HomeRepository,
) {
    suspend fun getPhotoInfo(
        photoId: String,
        tableName: PhotoTableName,
    ): Flow<PhotoDetail> =
        when (tableName) {
            PhotoTableName.New -> {
                homeRepository.getNewPhotoInfo(photoId)
            }
            PhotoTableName.Popular -> {
                homeRepository.getPoularPhotoInfo(photoId)
            }
        }
}
