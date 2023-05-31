package com.example.diplom.ui.news.detailednews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom.domain.entity.News
import com.example.diplom.domain.repo.INewsRepository
import com.example.diplom.utils.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class DetailedNewsViewModel(private val newsRepository: INewsRepository) : ViewModel() {

    private val _deatailedNewsStateFlow = MutableStateFlow<Event<List<News>>?>(null)
    val deatailedNewsStateFlow = _deatailedNewsStateFlow.asStateFlow().filterNotNull()

    init {
        getNews()
    }
    fun getNews() {
        viewModelScope.launch {
            try {
                _deatailedNewsStateFlow.emit(Event.loading())
                val schedule = newsRepository.getNews()
                _deatailedNewsStateFlow.emit(Event.success(schedule))
            } catch (e: Exception) {
                _deatailedNewsStateFlow.emit(Event.error(e))
            }
        }
    }
}