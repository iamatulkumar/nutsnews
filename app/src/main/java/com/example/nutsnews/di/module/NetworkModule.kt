package com.example.nutsnews.di.module

import com.example.nutsnews.BuildConfig
import com.example.nutsnews.data.remote.api.ArticleService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideArticleService(): ArticleService {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ArticleService::class.java)
    }
}