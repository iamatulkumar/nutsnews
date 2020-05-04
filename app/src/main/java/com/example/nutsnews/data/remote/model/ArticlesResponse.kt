package com.example.nutsnews.data.remote.model

import com.example.nutsnews.data.local.entity.ArticlesEntity
import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
        @SerializedName("status")
        val status: String = "",

        @SerializedName("source")
        val source: String = "",

        @SerializedName("sortBy")
        val sortBy: String = "",

        @SerializedName("articles")
        val articles: List<ArticlesEntity> = emptyList()
)