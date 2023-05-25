package com.example.diplom.ui.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom.data.dataSource.database.InMemoryCache
import com.example.diplom.data.remote.network.NetworkErrors
import com.example.diplom.domain.Requests
import com.example.diplom.domain.entity.News
import com.example.diplom.domain.repo.INewsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class NewsViewModel(private val repo: INewsRepo) : ViewModel() {

    private val _newsState = MutableStateFlow<List<News>?>(null)
    val newsStateFlow = _newsState.asStateFlow().filterNotNull()
    private val _error = MutableStateFlow<NetworkErrors?>(null)

    init {
        getNews()
    }
    fun getNews() {
        viewModelScope.launch {
            when (val result = repo.getNews()) {
                is Requests.Success -> {
                    _newsState.emit(result.data)
                }
                is Requests.Error -> {
                    _error.emit(result.exception)
                }
            }
        }
    }
}