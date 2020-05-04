package com.example.nutsnews

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nutsnews.data.local.AppDatabase
import com.example.nutsnews.data.local.entity.ArticlesEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticlesDaoTest {

    private lateinit var db: AppDatabase

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext<Context>(), AppDatabase::class.java).build()
    }

    @After
    fun closeDb() = db.close()

    @Test
    @Throws(InterruptedException::class)
    fun insertNewsArticles() {
        // GIVEN
        val input = listOf(ArticlesEntity(1), ArticlesEntity(2))

        // THEN
        assertThat(db.newsArticlesDao().insertArticles(input), equalTo(listOf(1L, 2L)))
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertNewsArticlesAndRead(): Unit = runBlocking {
        // GIVEN
        val input = listOf(
            ArticlesEntity(1, "First", "Hello"),
            ArticlesEntity(2, "Second", "Testing")
        )
        db.newsArticlesDao().insertArticles(input)

        // THEN
        val articles = db.newsArticlesDao().getNewsArticles()
        assertThat(articles, equalTo(articles))
    }

}