package com.example.nutsnews.data.local

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nutsnews.data.local.dao.ArticlesDao
import com.example.nutsnews.data.local.entity.ArticlesEntity

@Database(
        entities = [ArticlesEntity::class],
        version = 1
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Get news article DAO
     */
    abstract fun newsArticlesDao(): ArticlesDao

    companion object {

        private const val databaseName = "article-db"

        fun buildDefault(context: Context) =
                Room.databaseBuilder(context, AppDatabase::class.java,
                    databaseName
                )
                        .build()

        @VisibleForTesting
        fun buildTest(context: Context) =
                Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                        .build()
    }
}