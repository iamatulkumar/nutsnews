package com.example.nutsnews.di.module

import androidx.lifecycle.ViewModel
import com.example.nutsnews.di.base.ViewModelKey
import com.example.nutsnews.viewmodel.ArticleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ArticleViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel::class)
    fun bindNewsArticleViewModel(newsArticleViewModel: ArticleViewModel): ViewModel
}