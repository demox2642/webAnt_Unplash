package com.example.webant_unsplush.di
import com.example.data.database.UnsplashDatabase
import com.example.data.repository.HomeRepositoryImpl
import com.example.data.service.HomeService
import com.example.domain.repository.HomeRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideUrl(): String = "https://api.unsplash.com"

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        baseUrl: String,
        client: OkHttpClient,
    ): Retrofit.Builder {
        val gson =
            GsonBuilder()
                .setLenient()
                .create()

        return Retrofit
            .Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideHomeService(retrofit: Retrofit.Builder): HomeService =
        retrofit
            .build()
            .create(HomeService::class.java)

    @Singleton
    @Provides
    fun provideHomeRepository(
        homeService: HomeService,
        unsplashDatabase: UnsplashDatabase,
    ): HomeRepository = HomeRepositoryImpl(homeService, unsplashDatabase)
}
