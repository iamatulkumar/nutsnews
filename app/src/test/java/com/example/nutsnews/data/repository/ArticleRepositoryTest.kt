package com.example.nutsnews.data.repository

import com.example.nutsnews.data.ViewState
import com.example.nutsnews.data.local.dao.ArticlesDao
import com.example.nutsnews.data.local.entity.ArticlesEntity
import com.example.nutsnews.data.remote.api.ArticleService
import com.example.nutsnews.data.remote.model.ArticlesResponse
import com.example.nutsnews.utils.MockitoTest
import com.example.nutsnews.utils.assertItems
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import retrofit2.Response

@RunWith(JUnit4::class)
class ArticleRepositoryTest : MockitoTest() {

    @Mock
    lateinit var articlesDao: ArticlesDao

    @Mock
    lateinit var articleService: ArticleService

    @InjectMocks
    lateinit var newsRepository: DefaultNewsRepository

    @Test
    fun `get news articles when there is internet`() = runBlocking {
        // GIVEN
        val cachedArticles = listOf(ArticlesEntity(title = "Cached"))
        val fetchedArticles = listOf(ArticlesEntity(title = "Fetched"))
        val newsSource = ArticlesResponse(articles = fetchedArticles)
        val response = Response.success(newsSource)

        // WHEN
        whenever(articleService.getArticles()) doReturn response
        whenever(articlesDao.getNewsArticles()) doReturnConsecutively listOf(flowOf(cachedArticles), flowOf(fetchedArticles))

        // THEN
        newsRepository.getNewsArticles().assertItems(
                ViewState.loading(),
                ViewState.success(cachedArticles),
                ViewState.success(fetchedArticles)
        )
    }

    @Test
    fun `get cached news articles when there is no internet`() = runBlocking {
        // GIVEN
        val cachedArticles = listOf(ArticlesEntity(title = "Cached"))
        val error = RuntimeException("Unable to fetch from network")

        // WHEN
        whenever(articleService.getArticles()) doThrow error
        whenever(articlesDao.getNewsArticles()) doReturn flowOf(cachedArticles)

        // THEN
        newsRepository.getNewsArticles().assertItems(
                ViewState.loading(),
                ViewState.success(cachedArticles),
                ViewState.error(error.message.orEmpty())
        )
    }
}