package com.example.nutsnews.data.repository

import com.example.nutsnews.data.NetworkBoundResource
import com.example.nutsnews.data.ViewState
import com.example.nutsnews.data.local.dao.ArticlesDao
import com.example.nutsnews.data.local.entity.ArticlesEntity
import com.example.nutsnews.data.remote.api.ArticleService
import com.example.nutsnews.data.remote.model.ArticlesResponse
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 */
interface ArticleRepository {

    /**
     * Gets tne cached news article from database and tries to get
     * fresh news articles from web and save into database
     * if that fails then continues showing cached data.
     */
    fun getNewsArticles(): Flow<ViewState<List<ArticlesEntity>>>
}

@Singleton
class DefaultNewsRepository @Inject constructor(
    private val articlesDao: ArticlesDao,
    private val articleService: ArticleService
) : ArticleRepository {

    override fun getNewsArticles(): Flow<ViewState<List<ArticlesEntity>>> {
        return object : NetworkBoundResource<List<ArticlesEntity>, ArticlesResponse>() {
            override suspend fun saveNetworkResult(response: ArticlesResponse) = articlesDao.clearAndCacheArticles(response.articles)
            // Always try to fetch new articles
            override fun shouldFetch(data: List<ArticlesEntity>?): Boolean = true
            override fun loadFromDb(): Flow<List<ArticlesEntity>> = articlesDao.getNewsArticles()
            override suspend fun fetchFromNetwork(): Response<ArticlesResponse> = articleService.getArticles()
        }
        .asFlow()
        .flowOn(Dispatchers.IO)
    }
}

@Module
interface ArticleRepositoryModule {
    /* Exposes the concrete implementation for the interface */
    @Binds fun it(it: DefaultNewsRepository): ArticleRepository
}