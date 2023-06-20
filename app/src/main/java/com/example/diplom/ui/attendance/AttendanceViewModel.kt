package com.example.diplom.ui.attendance

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom.data.remote.entity.ApiHeadSendList
import com.example.diplom.domain.entity.DisciplineDbEntity
import com.example.diplom.domain.entity.StudentOfGroup
import com.example.diplom.domain.repo.IDisciplineRepo
import com.example.diplom.domain.repo.IGroupRepo
import com.example.diplom.domain.repo.IHeadGroupListSendRepository
import com.example.diplom.utils.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class AttendanceViewModel(
    private val groupRepository: IGroupRepo,
    private val disciplinesRepository: IDisciplineRepo,
    private val sendRepository: IHeadGroupListSendRepository
) : ViewModel() {

    private val _groupState = MutableStateFlow<Event<List<StudentOfGroup>>?>(null)
    val groupState = _groupState.asStateFlow().filterNotNull()

    private val _disciplinesState = MutableStateFlow<Event<List<DisciplineDbEntity>>?>(null)
    val disciplinesState = _disciplinesState.asStateFlow().filterNotNull()

    private val _disciplineTypesState = MutableStateFlow<Event<List<String>>?>(null)
    val disciplineTypesState  = _disciplineTypesState.asStateFlow().filterNotNull()

    private val _teacherFioState = MutableStateFlow<Event<String>?>(null)
    val teacherFioState = _teacherFioState.asStateFlow().filterNotNull()

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

    fun getDisciplines() {
        viewModelScope.launch {
            try {
                _disciplinesState.value = Event.loading()
                val disciplines = disciplinesRepository.getDisciplinesFromDb()
                _disciplinesState.value = Event.success(disciplines)
            } catch (e: Exception) {
                _disciplinesState.value = Event.error(e)
            }
        }
    }

    fun getDisciplineTypes(discipline: String) {
        viewModelScope.launch {
            try {
                val types = disciplinesRepository.getDisciplineTypesByName(discipline)
                _disciplineTypesState.value = Event.success(types)
            } catch (e: Exception) {
                _disciplineTypesState.value = Event.error(e)
            }
        }
    }

    fun getTecherFIO(discipline: String, lessonType: String) {
        viewModelScope.launch {
            try {
                val teacherFIO = disciplinesRepository.getTeacherFIOfromDB(discipline, lessonType)
                _teacherFioState.value = Event.success(teacherFIO)
            } catch (e: Exception) {
                _teacherFioState.value = Event.error(e)
            }
        }
    }

    fun sendGroupList(grouplist: ApiHeadSendList) {
        viewModelScope.launch {
            val temp = sendRepository.sendgrouplistforconfirmation(grouplist)
            Log.println(Log.WARN,"CUSTOM",temp)
        }
    }
}

