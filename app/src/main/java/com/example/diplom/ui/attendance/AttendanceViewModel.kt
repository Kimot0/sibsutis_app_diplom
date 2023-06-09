package com.example.diplom.ui.attendance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom.domain.entity.StudentOfGroup
import com.example.diplom.domain.repo.IGroupRepo
import com.example.diplom.utils.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class AttendanceViewModel(
    val groupRepository: IGroupRepo
) : ViewModel() {

    private val _groupState = MutableStateFlow<Event<List<StudentOfGroup>>?>(null)
    val groupState = _groupState.asStateFlow().filterNotNull()

    fun getGroup() {
        viewModelScope.launch {
            try {
                _groupState.value = Event.loading()
                val group = groupRepository.getGroupFromDb()
                _groupState.value = Event.success(group)
            } catch (e: Exception) {
                _groupState.value = Event.error(e)
            }
        }
    }
}

