package com.example.diplom.ui.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom.data.dataSource.database.InMemoryCache
import com.example.diplom.domain.entity.Lesson
import com.example.diplom.domain.entity.ScheduleRequest
import com.example.diplom.domain.repo.IScheduleRepository
import com.example.diplom.utils.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class ScheduleViewModel(
    private val scheduleRepository: IScheduleRepository,
) : ViewModel() {
    val tabTitles = arrayOf(
        "ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ"
    )

    private val _scheduleStateFlow = MutableStateFlow<Event<List<Lesson>>?>(null)
    var scheduleStateFlow = _scheduleStateFlow.asStateFlow().filterNotNull()

    private val _dailyScheduleStateFlow = MutableStateFlow<Event<List<Lesson>>?>(null)
    var dailyScheduleStateFlow = _dailyScheduleStateFlow.asStateFlow().filterNotNull()

    fun getShedule(groupID: ScheduleRequest) {
        viewModelScope.launch {
            try {
                _scheduleStateFlow.emit(Event.loading())
                val schedule = scheduleRepository.getSchedule(groupID)
                _scheduleStateFlow.emit(Event.success(schedule))
            } catch (e: Exception) {
                _scheduleStateFlow.emit(Event.error(e))
            }
        }
    }

    fun getScheduleFromDatabase(groupID: String, weekDay: String) {
        viewModelScope.launch {
            try {
                _dailyScheduleStateFlow.emit(Event.loading())
                val dailySchedule =
                    scheduleRepository.getScheduleFromDatabaseByWeekDay(groupID, weekDay)
                _dailyScheduleStateFlow.emit(Event.success(dailySchedule))
            } catch (e: Exception) {
                _dailyScheduleStateFlow.emit(Event.error(e))
            }
        }
    }

}