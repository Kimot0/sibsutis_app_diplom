package com.example.diplom.data.repo

import com.example.diplom.data.dataSource.SibsutisRemoteDataSource
import com.example.diplom.data.remote.entity.toNews
import com.example.diplom.domain.Requests
import com.example.diplom.domain.entity.News
import com.example.diplom.domain.repo.INewsRepo

class NewsRepo(private val source: SibsutisRemoteDataSource):INewsRepo{
    override suspend fun getNews(): Requests<List<News>> {
        return when (val result = source.getNews()) {
            is Requests.Success -> {
                Requests.Success(
                    result.data.map{it.toNews()}
                )
            }
            is Requests.Error -> {
                Requests.Error(
                    result.exception
                )
            }
        }
    }
}