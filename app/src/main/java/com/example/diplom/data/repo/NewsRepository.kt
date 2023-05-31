package com.example.diplom.data.repo

import com.example.diplom.data.dataSource.SibsutisRemoteDataSource
import com.example.diplom.data.dataSource.database.AppDatabase
import com.example.diplom.data.remote.entity.ApiDbNews
import com.example.diplom.data.remote.entity.toApiDbNews
import com.example.diplom.data.remote.entity.toNews
import com.example.diplom.domain.Requests
import com.example.diplom.domain.entity.News
import com.example.diplom.domain.repo.INewsRepository

class NewsRepository(
    private val source: SibsutisRemoteDataSource,
    private val database: AppDatabase
) : INewsRepository {
    override suspend fun getNews(): List<News>? {
        val databaseNews = getNewsFromDatabase()
        return databaseNews.ifEmpty {
            val result = source.getNews()
            return result?.map {
                database.getNewsDao().insertNews(it.toApiDbNews())
                it.toNews()
            }
        }
    }

    override suspend fun getNewsFromDatabase():List<News> {
        return database.getNewsDao().getAllNews().map {
            it.toNews()
        }
    }

    override suspend fun insertNewsInDatabase(news:ApiDbNews) {
        database.getNewsDao().insertNews(news)
    }
}