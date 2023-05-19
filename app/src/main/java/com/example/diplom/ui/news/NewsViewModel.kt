package com.example.diplom.ui.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom.data.dataSource.database.InMemoryCache
import com.example.diplom.domain.Requests
import com.example.diplom.domain.repo.INewsRepo
import kotlinx.coroutines.launch

class NewsViewModel(private val repo: INewsRepo) : ViewModel() {
    init {
        getNews()
    }
    fun getNews() {
        viewModelScope.launch {
            when (val result = repo.getNews()) {
                is Requests.Success -> {
                    InMemoryCache.news.clear()
                    result.data.forEach {
                        InMemoryCache.news.add(it)
                    }
                    result.data
                }
                is Requests.Error -> {
                    result.exception
                }
            }
        }
    }
}