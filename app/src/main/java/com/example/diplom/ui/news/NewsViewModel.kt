package com.example.diplom.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom.domain.entity.News
import com.example.diplom.domain.repo.INewsRepository
import com.example.diplom.utils.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: INewsRepository) : ViewModel() {

    private val _newsStateFlow = MutableStateFlow<Event<List<News>>?>(null)
    val newsStateFlow = _newsStateFlow.asStateFlow().filterNotNull()

    init {
        getNews()
    }

    fun getNews() {
        viewModelScope.launch {
            try {
                _newsStateFlow.emit(Event.loading())
                val schedule = newsRepository.getNews()
                _newsStateFlow.emit(Event.success(schedule))
            } catch (e: Exception) {
                _newsStateFlow.emit(Event.error(e))
            }
        }
    }
}