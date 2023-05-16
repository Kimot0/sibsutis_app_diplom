package com.example.diplom.domain.repo

import com.example.diplom.domain.Requests
import com.example.diplom.domain.entity.News

interface INewsRepo {
    suspend fun getNews(): Requests<List<News>>
}