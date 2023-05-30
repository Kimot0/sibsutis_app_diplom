package com.example.diplom.data.repo

import com.example.diplom.data.dataSource.SibsutisRemoteDataSource
import com.example.diplom.data.dataSource.database.AppDatabase
import com.example.diplom.data.remote.entity.ApiDbNews
import com.example.diplom.data.remote.entity.toApiDbNews
import com.example.diplom.data.remote.entity.toNews
import com.example.diplom.domain.Requests
import com.example.diplom.domain.entity.News
import com.example.diplom.domain.repo.INewsRepo

class NewsRepo(
    private val source: SibsutisRemoteDataSource,
    private val database: AppDatabase
) : INewsRepo {
    override suspend fun getNews(): Requests<List<News>> {
        return when (val result = source.getNews()) {
            is Requests.Success -> {
                database.getNewsDao().deleteOldNews()
                Requests.Success(
                    result.data.map {
                        with(database.getNewsDao()) {
                            insertNews(it.toApiDbNews())
                        }
                        it.toNews()
                    }
                )
            }

            is Requests.Error -> {
                Requests.Error(
                    result.exception
                )
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