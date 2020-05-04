package com.example.nutsnews.data.remote.api

import com.example.nutsnews.BuildConfig
import com.example.nutsnews.data.remote.model.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ArticleService {

    @GET("top-headlines?sources=bbc-news&apiKey=${BuildConfig.NEWS_API_KEY}")
    suspend fun getArticles(): Response<ArticlesResponse>

}
