package com.example.shacklehotelbuddy.di

import android.content.Context
import androidx.room.Room
import com.example.shacklehotelbuddy.BuildConfig
import com.example.shacklehotelbuddy.common.Constants.HTTP_REQUEST_TIMEOUT
import com.example.shacklehotelbuddy.data.local.AppDatabase
import com.example.shacklehotelbuddy.data.local.AppDatabase.Companion.DATABASE_NAME
import com.example.shacklehotelbuddy.data.local.dao.SearchHistoryDao
import com.example.shacklehotelbuddy.data.remote.RapidApiService
import com.example.shacklehotelbuddy.data.repo.PropertyRepoImpl
import com.example.shacklehotelbuddy.domain.repo.PropertyRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRequestInterceptor(): Interceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val url = originalUrl.newBuilder().build()
        val requestBuilder = originalRequest.newBuilder()
            .url(url).header("X-RapidAPI-Key", BuildConfig.API_KEY)
            .header("X-RapidAPI-Host", BuildConfig.API_HOST)
            .header("content-type", "application/json")
        val request = requestBuilder.build()
        chain.proceed(request)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        requestInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY ))
            .connectTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideRapidApiService(retrofit: Retrofit): RapidApiService =
        retrofit.create(RapidApiService::class.java)

    @Singleton
    @Provides
    fun providePropertyRepo(
        rapidApiService: RapidApiService,
        searchHistoryDao: SearchHistoryDao
    ): PropertyRepo =
        PropertyRepoImpl(rapidApiService, searchHistoryDao)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideSearchHistoryDao(appDatabase: AppDatabase) = appDatabase.searchHistoryDao()

}