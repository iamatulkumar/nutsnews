package com.example.nutsnews.di.module

import android.app.Application
import com.example.nutsnews.data.local.AppDatabase
import com.example.nutsnews.data.local.dao.ArticlesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppDatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppDatabase = AppDatabase.buildDefault(app)

    @Singleton
    @Provides
    fun provideArticleDao(db: AppDatabase): ArticlesDao = db.newsArticlesDao()
}