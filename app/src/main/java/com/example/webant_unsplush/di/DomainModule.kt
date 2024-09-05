package com.example.webant_unsplush.di

import com.example.domain.repository.HomeRepository
import com.example.domain.usecase.GetNewPhotoUseCase
import com.example.domain.usecase.GetPhotoInfoUseCase
import com.example.domain.usecase.GetPopularPhotoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideGetPhotoInfoUseCase(homeRepository: HomeRepository) = GetPhotoInfoUseCase(homeRepository)

    @Provides
    fun provideGerPopularPhotoUseCase(homeRepository: HomeRepository) = GetPopularPhotoUseCase(homeRepository)

    @Provides
    fun provideGetNewPhotoUseCase(homeRepository: HomeRepository) = GetNewPhotoUseCase(homeRepository)
}
