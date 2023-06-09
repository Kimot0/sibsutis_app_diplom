package com.example.diplom.ui.schedule.scheduleSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom.domain.entity.ScheduleGroups
import com.example.diplom.domain.repo.IScheduleGroupsRepository
import com.example.diplom.utils.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class ScheduleSearchViewModel(
    private val scheduleGroupsRepository: IScheduleGroupsRepository,
): ViewModel() {
    private val _scheduleSearchStateFlow = MutableStateFlow<Event<List<ScheduleGroups>>?>(null)
    val scheduleSearchStateFlow = _scheduleSearchStateFlow.asStateFlow().filterNotNull()

    init {
        getGroups()
    }

    fun getGroups() {
        viewModelScope.launch {
            try {
                _scheduleSearchStateFlow.emit(Event.loading())
                val groups = scheduleGroupsRepository.getGroups()
                _scheduleSearchStateFlow.emit(Event.success(groups))
            } catch (e: Exception) {
                _scheduleSearchStateFlow.emit(Event.error(e))
            }
        }
    }
}