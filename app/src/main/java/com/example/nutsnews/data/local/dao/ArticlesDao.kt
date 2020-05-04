package com.example.nutsnews.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.nutsnews.data.local.entity.ArticlesEntity
import kotlinx.coroutines.flow.Flow

/**
 * Defines access layer to news articles table
 */
@Dao
interface ArticlesDao {

    /**
     * Insert articles into the table
     */
    @Insert
    fun insertArticles(articles: List<ArticlesEntity>): List<Long>

    @Query("DELETE FROM article")
    fun clearAllArticles()

    @Transaction
    fun clearAndCacheArticles(articles: List<ArticlesEntity>) {
        clearAllArticles()
        insertArticles(articles)
    }

    /**
     * Get all the articles from table
     */
    @Query("SELECT * FROM article")
    fun getNewsArticles(): Flow<List<ArticlesEntity>>

    @Query("SELECT * FROM article WHERE id=:articleNumber")
    fun getNewsArticlesByNumber(articleNumber: Int): Flow<ArticlesEntity>
}