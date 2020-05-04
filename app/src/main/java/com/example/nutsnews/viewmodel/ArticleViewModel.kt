package com.example.nutsnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.nutsnews.data.ViewState
import com.example.nutsnews.data.local.entity.ArticlesEntity
import com.example.nutsnews.data.repository.ArticleRepository
import javax.inject.Inject

class ArticleViewModel @Inject constructor(
        newsRepository: ArticleRepository
) : ViewModel() {

    private val newsArticles: LiveData<ViewState<List<ArticlesEntity>>> = newsRepository.getNewsArticles().asLiveData()

    /**
     * Return news articles
     */
    fun getArticles(): LiveData<ViewState<List<ArticlesEntity>>> = newsArticles

}