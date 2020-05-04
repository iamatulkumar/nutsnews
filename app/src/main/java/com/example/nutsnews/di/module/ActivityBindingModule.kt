package com.example.nutsnews.di.module

import com.example.nutsnews.activity.ArticleActivity
import com.example.nutsnews.activity.ArticleDetailsActivity
import com.example.nutsnews.data.repository.ArticleRepositoryModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [
        ArticleViewModelModule::class,
        ArticleRepositoryModule::class
    ]
)
interface ActivityBindingModule {

    @ContributesAndroidInjector
    fun contributeArticleActivity(): ArticleActivity

    @ContributesAndroidInjector
    fun contributeArticleDetailsActivity(): ArticleDetailsActivity
}